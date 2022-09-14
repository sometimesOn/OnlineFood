package com.jia.bulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.bulu.entity.DishFlavor;
import com.jia.bulu.mapper.DishFlavorMapper;
import com.jia.bulu.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
