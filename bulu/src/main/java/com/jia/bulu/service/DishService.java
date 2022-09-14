package com.jia.bulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jia.bulu.dto.DishDto;
import com.jia.bulu.entity.Dish;

public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表dish,dish_flavor
    public void saveWithFlavor(DishDto dishDto);


    //根据id查询菜品的对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品，同时更新菜品对应的口味数据，需要操作两张表dish,dish_flavor
    public void updateWithFlavor(DishDto dishDto);

    //更新status
    public boolean updateStatus(Long id,Integer status);
}
