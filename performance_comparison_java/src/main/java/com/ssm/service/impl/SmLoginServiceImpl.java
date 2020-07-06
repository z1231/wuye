package com.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssm.entity.SmLogin;
import com.ssm.mapper.SmLoginMapper;
import com.ssm.service.ISmLoginService;

@Service
public class SmLoginServiceImpl extends ServiceImpl<SmLoginMapper, SmLogin> implements ISmLoginService {

}
