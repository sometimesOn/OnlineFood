package com.jia.bulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.bulu.dto.SetmealDto;
import com.jia.bulu.entity.Setmeal;
import com.jia.bulu.entity.SetmealDish;
import com.jia.bulu.mapper.SetmealMapper;
import com.jia.bulu.service.SetmealDishService;
import com.jia.bulu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {


        //保存套餐的基本信息，操作setmeal
        this.save(setmealDto);


        List<SetmealDish> setmealDishList = setmealDto.getSetmealDishList();
        setmealDishList.stream().map((item) ->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联关系，操作setmeal_dish
        setmealDishService.saveBatch(setmealDishList);

    }

    /**
     * 获取套餐，同时获取和套餐相关的菜品信息
     * @param id
     */
    @Override
    public SetmealDto getWithSetmealDish(Long id) {

        //根据id获取套餐的基本信息
        Setmeal setmeal = this.getById(id);

        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);

        //根据套餐id获取套餐相关的菜品信息
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,setmeal.getId());

        //执行查询
        List<SetmealDish> setmealDishList = setmealDishService.list(queryWrapper);
        setmealDto.setSetmealDishList(setmealDishList);

        return setmealDto;
    }


    /**
     * 更新套餐状态信息
     * @param status
     * @param id
     * @return
     */
    @Override
    public boolean updateStatus(Integer status, Long id) {

        return setmealMapper.updateStatus(status,id);

    }
}
