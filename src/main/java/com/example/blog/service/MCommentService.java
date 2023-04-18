package com.example.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.entity.MComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.vo.CommentVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kris
 * @since 2023-03-14
 */

public interface MCommentService extends IService<MComment> {

    IPage<CommentVo> paging(Page page, Long postId, Long userId, String order);
}
