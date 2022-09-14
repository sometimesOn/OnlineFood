package com.jia.bulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.bulu.dto.DishDto;
import com.jia.bulu.entity.Dish;
import com.jia.bulu.entity.DishFlavor;
import com.jia.bulu.mapper.DishMapper;
import com.jia.bulu.service.DishFlavorService;
import com.jia.bulu.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private DishMapper dishMapper;


    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {

        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);

        Long dishDtoId = dishDto.getId();//菜品id

        //菜品口味
        List<DishFlavor> dishFlavors = dishDto.getFlavors();
        for (DishFlavor dishFlavor : dishFlavors){
            dishFlavor.setDishId(dishDtoId);
        }

        //保存菜品口味数据到口味表dish_flavor
        dishFlavorService.saveBatch(dishFlavors);

    }


    /**
     * 根据id查询菜品的对应的口味信息
     * @param id
     * @return
     */
    public DishDto getByIdWithFlavor(Long id){

        //查询菜品的基本信息，从dish表查询
        Dish dish = this.getById(id);


        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        //查询当前菜品对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> dishFlavorList = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(dishFlavorList);

        return dishDto;
    }

    /**
     * 更新菜品，同时更新对应的口味数据
     * @param dishDto
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {

        //更新dish的基本信息
        this.updateById(dishDto);

        //清理当前菜品对应的口味数据--delete
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());

        dishFlavorService.remove(queryWrapper);

        //添加当前提交过来的口味数据--insert
        List<DishFlavor> dishFlavors = dishDto.getFlavors();
        for (DishFlavor dishFlavor : dishFlavors){
            dishFlavor.setDishId(dishDto.getId());
        }

        dishFlavorService.saveBatch(dishFlavors);


    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        return dishMapper.updateStatus(id,status);
    }

}
