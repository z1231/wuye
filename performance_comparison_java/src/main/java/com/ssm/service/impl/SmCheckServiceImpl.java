package com.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssm.entity.SmCheck;
import com.ssm.mapper.SmCheckMapper;
import com.ssm.service.ISmCheckService;
@Service
public class SmCheckServiceImpl extends ServiceImpl<SmCheckMapper, SmCheck> implements ISmCheckService {

}
