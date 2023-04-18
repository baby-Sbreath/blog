package com.example.blog.controller;


import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.blog.controller.BaseController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kris
 * @since 2023-03-14
 */
@Controller
public class MUserController extends BaseController {

    @Autowired
    Producer producer;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse resp) throws IOException {
        //验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

        //设置前端不缓存该验证码图片
        resp.setHeader("Cache-Control", "no-store, no-cache");
        resp.setContentType("image/jpeg");

        

        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }

    @GetMapping ("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/register")
    public String register(){
        return "user/reg";
    }

    @PostMapping("/register")
    public String doRegister(){

        return "user/reg";
    }
}
