package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.entity.MPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.vo.PostVo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kris
 * @since 2023-03-14
 */
@Component
public interface MPostMapper extends BaseMapper<MPost> {

    IPage<PostVo> selectPosts(Page page, @Param(Constants.WRAPPER) QueryWrapper ew);

    PostVo selectOnePost(@Param(Constants.WRAPPER) QueryWrapper<MPost> ew);
}
