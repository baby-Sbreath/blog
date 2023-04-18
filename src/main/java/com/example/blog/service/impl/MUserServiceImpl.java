package com.example.blog.service.impl;

import com.example.blog.entity.MUser;
import com.example.blog.mapper.MUserMapper;
import com.example.blog.service.MUserService;
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
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

}
