package com.jia.bulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.bulu.entity.OrderDetail;
import com.jia.bulu.mapper.OrderDetailMapper;
import com.jia.bulu.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
