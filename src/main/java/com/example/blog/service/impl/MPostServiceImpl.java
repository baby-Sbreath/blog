package com.example.blog.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.entity.MPost;
import com.example.blog.mapper.MPostMapper;
import com.example.blog.service.MPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.util.RedisUtil;
import com.example.blog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kris
 * @since 2023-03-14
 */
@Service
public class MPostServiceImpl extends ServiceImpl<MPostMapper, MPost> implements MPostService {

    @Autowired
    MPostMapper postMapper;

    @Autowired
    RedisUtil redisUtil;

    public IPage<PostVo> paging(Page page, Long categoryId, Long userId, Integer level, boolean recommend, String order){

        if(level == null) level = -1;

        QueryWrapper wrapper = new QueryWrapper<MPost>()
                .eq(categoryId!=null,"category_id",categoryId)
                .eq(userId!=null,"user_id",userId)
                .eq(level==0,"level",0)
                .gt(level>0,"level",0)
                .ge(recommend==false,"recommend",0)
                .ge(recommend==true,"recommend",1)
                .orderByDesc(order!=null,order);

        return postMapper.selectPosts(page,wrapper);
    }

    @Override
    public PostVo selectOnePost(Long pId) {

        QueryWrapper wrapper = new QueryWrapper<MPost>()
                .eq("p.id",pId);

        return postMapper.selectOnePost(wrapper);
    }

    @Override
    public void initWeekRank() {
        //获取7天内发表的文章
        List<MPost> postList = this.list(
                new QueryWrapper<MPost>().ge("created", DateUtil.offsetDay(new Date(),-7))
                        .select("id, title, user_id, comment_count, view_count, created")
        );

        //初始化文章的总评论数
        for(MPost post : postList){
            String key = "day:rank:" + DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_FORMAT);
            //key day:rank:yyyyMMdd  value是文章的id  score是文字的评论数量
            redisUtil.zSet(key,post.getId(),post.getCommentCount());

            //设置过期时间，以当前本地时间为准，若离当前本地时间超过七天，则过期
            Long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
            long expireTime = (7 - between) * 24 * 60 * 60; // 有效时间
            redisUtil.expire(key,expireTime);

            //缓存文章的一些基本信息
            this.hashCachePostIdAndTitle(post, expireTime);
        }

        //做并集
        this.zUnionAndStoreLast7DayForWeekRank();

    }

    //合并每日评论数量  一周
    private void zUnionAndStoreLast7DayForWeekRank() {
        String currentKey = "day:rank:" + DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
        String destKey = "week:rank";
        List<String> otherKeys = new ArrayList<>();
        for(int i=-6; i<0; i++){
            String temp = "day:rank:" + DateUtil.format(DateUtil.offsetDay(new Date(),i), DatePattern.PURE_DATE_FORMAT);
            otherKeys.add(temp);
        }
        redisUtil.zUnionAndStore(currentKey,otherKeys,destKey);
    }

    //缓存文章的一些基本信息
    private void hashCachePostIdAndTitle(MPost post, long expireTime) {
        String key = "rank:post:"+post.getId();
        boolean hasKey = redisUtil.hasKey(key);
        if(!hasKey){
            redisUtil.hset(key,"post:id",post.getId(),expireTime);
            redisUtil.hset(key,"post:title",post.getTitle(),expireTime);
            redisUtil.hset(key,"post:commentCount",post.getCommentCount(),expireTime);
            redisUtil.hset(key,"post:viewCount",post.getViewCount(),expireTime);
        }
    }


}
