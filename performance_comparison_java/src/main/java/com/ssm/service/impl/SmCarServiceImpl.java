package com.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssm.entity.SmCar;
import com.ssm.mapper.SmCarMapper;
import com.ssm.service.ISmCarService;
@Service
public class SmCarServiceImpl extends ServiceImpl<SmCarMapper, SmCar> implements ISmCarService {

}
