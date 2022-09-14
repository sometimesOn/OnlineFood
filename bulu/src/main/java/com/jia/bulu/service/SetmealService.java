package com.jia.bulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jia.bulu.dto.SetmealDto;
import com.jia.bulu.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 获取套餐，同时获取和套餐相关的菜品信息
     * @param id
     */
    SetmealDto getWithSetmealDish(Long id);


    /**
     * 更新套餐状态信息
     * @param status
     * @param id
     * @return
     */
    public boolean updateStatus(Integer status , Long id);
}
