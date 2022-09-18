package com.jia.bulu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.bulu.common.R;
import com.jia.bulu.dto.DishDto;
import com.jia.bulu.entity.Category;
import com.jia.bulu.entity.Dish;
import com.jia.bulu.entity.DishFlavor;
import com.jia.bulu.service.CategoryService;
import com.jia.bulu.service.DishFlavorService;
import com.jia.bulu.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 菜品分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,
                        int pageSize,
                        String name){

        //分页构造器
        Page<Dish> dishPage = new Page<>(page,pageSize);


        Page<DishDto> dishDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        //过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);

        //排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行查询
        dishService.page(dishPage,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(dishPage,dishDtoPage,"records");

        List<Dish> records = dishPage.getRecords();

        List<DishDto> list = records.stream().map((item) -> {

            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();//分类id
            Category category = categoryService.getById(categoryId);

            String categoryName = category.getName();

            dishDto.setCategoryName(categoryName);

            return dishDto;

        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);


        return R.success(dishDtoPage);
    }


    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){

        dishService.saveWithFlavor(dishDto);

        return R.success("新增菜品成功");
    }

    /**
     * 根据id查询菜品的对应的口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Cacheable(value = "DishDto",key = "#id",condition = "#result != null ")
    public R<DishDto> get(@PathVariable("id") Long id){

        DishDto dishDto = dishService.getByIdWithFlavor(id);

        return R.success(dishDto);

    }

    /**
     * 修改菜品和它对应的口味表
     * @param dishDto
     * @return
     */
    @PutMapping
    @CacheEvict(value = "DishDto",key = "#dishDto.id")
    public R<String> update(@RequestBody DishDto dishDto){

        dishService.updateWithFlavor(dishDto);

        return R.success("修改菜品信息成功");
    }

    /**
     * 修改菜品状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable("status") Integer status,
                                  Long[] ids){


        for (Long id : ids){

            dishService.updateStatus(id,status);

        }

        return R.success("停售成功");

    }

    /**
     * 删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam("ids") Long[] ids){

        for (Long id : ids){

            dishService.removeById(id);

        }

        return R.success("菜品删除成功");
    }

    /**
     * 根据菜品分类id查找相应的菜品集合
     * @param dish
     * @return
     */
//    @GetMapping("/list")
//    public R<List<Dish>> list(Dish dish){
//
//        log.info("dish: {}",dish.getCategoryId());
//
//        //条件构造器
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(dish.getCategoryId() != null,Dish::getCategoryId,dish.getCategoryId());
//        //添加条件，状态为1，
//        queryWrapper.eq(Dish::getStatus,1);
//
//        //添加排序条件
//        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
//
//
//        List<Dish> list = dishService.list(queryWrapper);
//
//        return R.success(list);
//
//    }


    /**
     * 根据菜品分类id查找相应的菜品集合
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){

        log.info("dish: {}",dish.getCategoryId());

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null,Dish::getCategoryId,dish.getCategoryId());
        //添加条件，状态为1，
        queryWrapper.eq(Dish::getStatus,1);

        //添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);


        List<Dish> list = dishService.list(queryWrapper);


        List<DishDto> dishDtoList = list.stream().map((item) -> {
           DishDto dishDto  = new DishDto();

            BeanUtils.copyProperties(item,dishDto);

            //当前菜品的ID
            Long dishId = item.getId();

            LambdaQueryWrapper<DishFlavor> flavorLambdaQueryWrapper = new LambdaQueryWrapper<>();

            //添加条件
            flavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dishId);

            List<DishFlavor> dishFlavorList = dishFlavorService.list(flavorLambdaQueryWrapper);

            dishDto.setFlavors(dishFlavorList);

            return dishDto;


        }).collect(Collectors.toList());

        return R.success(dishDtoList);

    }

}
