package com.example.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.entity.MPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.vo.PostVo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kris
 * @since 2023-03-14
 */

public interface MPostService extends IService<MPost> {
    IPage<PostVo> paging(Page page, Long categoryId, Long userId, Integer level, boolean recommend, String order);

    PostVo selectOnePost(Long pId);

    void initWeekRank();
}
