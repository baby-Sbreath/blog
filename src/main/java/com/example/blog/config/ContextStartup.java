package com.example.blog.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.entity.MCategory;
import com.example.blog.service.MCategoryService;
import com.example.blog.service.MPostService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

@Component
public class ContextStartup implements ApplicationRunner, ServletContextAware {

    @Autowired
    MCategoryService mCategoryService;

    ServletContext servletContext;

    @Autowired
    MPostService postService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //项目启动时调用
        //获取博客分类列表
        List<MCategory> categories = mCategoryService.list(new QueryWrapper<MCategory>().eq("status", 0));
        servletContext.setAttribute("categories",categories);

        //侧边栏的本周热议
        postService.initWeekRank();

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
