package com.jia.bulu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jia.bulu.common.R;
import com.jia.bulu.entity.ShoppingCart;
import com.jia.bulu.service.ShoppingCartService;
import com.jia.bulu.utils.BaseContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * 查询当前用户的购物车
     * @return
     */
    @GetMapping("/list")
    @Cacheable(value = "shoppingCartCache",key = "'allShopping'")
    public R<List<ShoppingCart>> list(){

        //条件构造器
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContextUtil.getCurrentId());
        queryWrapper.orderByDesc(ShoppingCart::getCreateTime);


        //执行查询
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }


    /**
     * 新增菜品或套餐到购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    @CacheEvict(value = "shoppingCartCache",allEntries = true)
    public R<ShoppingCart> save(@RequestBody ShoppingCart shoppingCart){

        log.info("shoppingCart: {}",shoppingCart);

        //设置用户id，指定这是那个用户的购物车数据
        Long currentId = BaseContextUtil.getCurrentId();
        shoppingCart.setUserId(currentId);

        //查询当前菜品或者套餐是否在当前购物车中
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);

        if (dishId != null){
            //是菜品
            queryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());


        }else {
            //是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }

        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if (cartServiceOne != null){
            //如果已经存在，就在原来的数据上加一
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        }else {
            //如果不存在，则添加到购物车，数量默认是一
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);
    }


    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    @CacheEvict(value = "shoppingCartCache",allEntries = true)
    public R<String> clean(){

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContextUtil.getCurrentId());

        shoppingCartService.remove(queryWrapper);

        return R.success("清空购物车成功");
    }


    /**
     * 购物车中菜品或套餐的数量减一
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    @CacheEvict(value = "shoppingCartCache",allEntries = true)
    public R<String> sub(@RequestBody ShoppingCart shoppingCart){

        log.info("shoppingCart: {}",shoppingCart);

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();

        if (shoppingCart.getDishId() != null){
            queryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        }else {
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }

        ShoppingCart one = shoppingCartService.getOne(queryWrapper);
        Integer number = one.getNumber();
        one.setNumber(number - 1);
        shoppingCartService.updateById(one);
        return R.success("减一");

    }


}
