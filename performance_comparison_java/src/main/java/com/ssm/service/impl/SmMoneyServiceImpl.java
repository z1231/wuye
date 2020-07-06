package com.ssm.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssm.entity.SmMoney;
import com.ssm.mapper.SmMoneyMapper;
import com.ssm.service.ISmMoneyService;
@Service
public class SmMoneyServiceImpl extends ServiceImpl<SmMoneyMapper, SmMoney> implements ISmMoneyService {

}
