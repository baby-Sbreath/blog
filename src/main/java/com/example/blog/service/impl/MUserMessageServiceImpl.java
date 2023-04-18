package com.example.blog.service.impl;

import com.example.blog.entity.MUserMessage;
import com.example.blog.mapper.MUserMessageMapper;
import com.example.blog.service.MUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MUserMessageServiceImpl extends ServiceImpl<MUserMessageMapper, MUserMessage> implements MUserMessageService {

}
