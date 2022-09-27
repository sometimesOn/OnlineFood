package com.jia.bulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.bulu.common.R;
import com.jia.bulu.dto.SetmealDto;
import com.jia.bulu.entity.Setmeal;
import com.jia.bulu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @GetMapping("/page")
    public R<Page> page(int page,
                        int pageSize,
                        String name){

        //分页构造器
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);

        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        //执行查询
        setmealService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);

    }

    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    @CacheEvict(value = "setmealCache",allEntries = true)
    public R<String> save(@RequestBody SetmealDto setmealDto){

        log.info("setmealDto: {}",setmealDto.toString());

        setmealService.saveWithDish(setmealDto);

        return R.success("新增套餐信息成功");
    }


    /**
     * 根据id查找套餐及相关的菜品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<SetmealDto> get(@PathVariable Long id){

        log.info("id: {}",id);

        SetmealDto setmealDto = setmealService.getWithSetmealDish(id);

        return R.success(setmealDto);
    }

    /**
     * 更新套餐的状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    @CacheEvict(value = "setmealCache",allEntries = true)
    public R<String> updateStatus(@PathVariable("status") Integer status,
                                  Long[] ids){

        log.info("type: {},ids: {}",status,ids);

        for (Long id : ids){

            setmealService.updateStatus(status,id);

        }

        return R.success("套餐状态更新成功");

    }


    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @CacheEvict(value = "setmealCache",allEntries = true)
    public R<String> delete(Long[] ids){

        log.info("ids: {}",ids);

        for (Long id : ids){

            setmealService.removeById(id);

        }

        return R.success("套餐删除成功");

    }


    /**
     * 查询套餐及其相关的菜品表的集合
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    @Cacheable(value = "setmealCache",key = "#setmeal.categoryId + '_' + #setmeal.status")
    public R<List<Setmeal>> list(Setmeal setmeal){


        log.info("Setmeal: {}",setmeal);

        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(Setmeal::getStatus,setmeal.getStatus());

        //执行查询
        List<Setmeal> list = setmealService.list();

        return R.success(list);
    }



}
