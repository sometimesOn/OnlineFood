package com.jia.bulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.bulu.common.R;
import com.jia.bulu.entity.Category;
import com.jia.bulu.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 菜品分类的分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){

        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //排序条件
        queryWrapper.orderByAsc(Category::getSort);

        //执行查询
        categoryService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category {}",category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 根据id删除分类
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids){
        log.info("删除分类，id为 {}",ids);

        //categoryService.removeById(ids);
        categoryService.remove(ids);

        return R.success("分类删除成功");
    }


    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){

        categoryService.updateById(category);

        return R.success("修改分类信息成功");
    }

    /**
     * 根据条件来查询分类数据
     * @param type
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(@RequestParam(value = "type",required = false) String type){
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(StringUtils.isNotEmpty(type),Category::getType,type);
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        //执行查询
        List<Category> list = categoryService.list(queryWrapper);

        return R.success(list);

    }


}
