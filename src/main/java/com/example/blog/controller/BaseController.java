package com.example.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.service.MCommentService;
import com.example.blog.service.MPostService;
import com.example.blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class BaseController {

    @Autowired
    HttpServletRequest req;

    @Autowired
    MPostService mPostService;

    @Autowired
    MCommentService commentService;

    @Autowired
    RedisUtil redisUtil;


    public Page getPage(){
        int pn = ServletRequestUtils.getIntParameter(req, "pn", 1);
        int size = ServletRequestUtils.getIntParameter(req, "size", 2);
        return new Page(pn,size);
    }

    public List getHots(){
        //侧边栏
        String key = "week:rank";
        List<Map> hotsList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple> zSetRank = redisUtil.getZSetRank(key, 0, 6);
        for(ZSetOperations.TypedTuple typedTuple: zSetRank){
            Object value = typedTuple.getValue();
            //用于查找缓存中的文章的相关信息
            String postKey = "rank:post:"+value;

            Map<String,Object> map = new HashMap<>();
            map.put("id",value);
            map.put("title",redisUtil.hget(postKey,"post:title"));
            map.put("commentCount",redisUtil.hget(postKey,"post:commentCount"));
            map.put("viewCount",redisUtil.hget(postKey,"post:viewCount"));

            hotsList.add(map);
        }
        return hotsList;
    }

}
