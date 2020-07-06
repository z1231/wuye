package com.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssm.entity.SmUser;
import com.ssm.mapper.SmUserMapper;
import com.ssm.service.ISmUserService;
@Service
public class SmUserServiceImpl extends ServiceImpl<SmUserMapper, SmUser> implements ISmUserService {

}

