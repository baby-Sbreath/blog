package com.example.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.blog.vo.CommentVo;
import com.example.blog.vo.PostVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.blog.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kris
 * @since 2023-03-14
 */
@Controller
public class MPostController extends BaseController {

    @GetMapping("/category/{id:\\d*}")
    public String category(@PathVariable(name = "id") Long id){

        req.setAttribute("currentCategoryId",id);

        //侧边栏
        req.setAttribute("hotsList",getHots());
        return "post/category";
    }

    @GetMapping("/detail/{id:\\d*}")
    public String detail(@PathVariable(name = "id") Long id){

        //文章相关
        PostVo postVo = mPostService.selectOnePost(id);
        //判断是否存在该文章
        Assert.notNull(postVo,"文章已删除！");
        req.setAttribute("onePost",postVo);

        Long currentCategoryId = postVo.getCategoryId();
        req.setAttribute("currentCategoryId",currentCategoryId);

        //评论相关
        IPage<CommentVo> commentVoIPage = commentService.paging(getPage(),postVo.getId(),null,"created");
        req.setAttribute("comments",commentVoIPage);

        //侧边栏
        req.setAttribute("hotsList",getHots());

        return "post/detail";
    }
}
