package com.example.blog.vo;

import com.example.blog.entity.MPost;
import lombok.Data;

import java.util.Date;

@Data
public class PostVo extends MPost {
    //分类类别名称
    private String categoryName;

    //用户相关
    private Long authorId;
    private String authorName;
    private String authorAvatar;
}
