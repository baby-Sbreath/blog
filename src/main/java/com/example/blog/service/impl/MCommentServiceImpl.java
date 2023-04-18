package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.entity.MComment;
import com.example.blog.entity.MPost;
import com.example.blog.mapper.MCommentMapper;
import com.example.blog.service.MCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kris
 * @since 2023-03-14
 */
@Service
public class MCommentServiceImpl extends ServiceImpl<MCommentMapper, MComment> implements MCommentService {

    @Autowired
    MCommentMapper mCommentMapper;

    @Override
    public IPage<CommentVo> paging(Page page, Long postId, Long userId, String order) {

        QueryWrapper wrapper = new QueryWrapper<MComment>()
                .eq(postId!=null,"post_id",postId)
                .eq(userId!=null,"user_id",userId)
                .orderByDesc(order!=null,order);

        return mCommentMapper.selectComments(page,wrapper);
    }
}
