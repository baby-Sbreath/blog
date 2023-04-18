package com.example.blog.vo;

import com.example.blog.entity.MComment;
import lombok.Data;

@Data
public class CommentVo extends MComment {

    private Long authorId;
    private String authorName;
    private String authorAvatar;

}
