package com.example.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


@Controller
public class IndexController extends BaseController {

    @RequestMapping({"", "/","index"})
    public String index(){

        String recommendGet = req.getParameter("recommend");
        boolean recommend = false;
        if(recommendGet != null)
            if(recommendGet.equals("true"))
                recommend = true;

        //首页置顶部分
        IPage results = mPostService.paging(new Page(1,5), null, null, 1, false, "level");
        req.setAttribute("levelList",results);

        //首页综合部分
        IPage paging = mPostService.paging(getPage(), null, null, null, recommend, "created");
        req.setAttribute("paging",paging);

        //侧边栏部分
        req.setAttribute("hotsList",getHots());

        req.setAttribute("currentCategoryId",0);
        return "index";
    }

}
