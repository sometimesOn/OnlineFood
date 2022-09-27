package com.jia.bulu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jia.bulu.common.R;
import com.jia.bulu.entity.AddressBook;
import com.jia.bulu.service.AddressBookService;
import com.jia.bulu.utils.BaseContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {


    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址簿
     * @param addressBook
     * @return
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){

        addressBook.setUserId(BaseContextUtil.getCurrentId());
        addressBookService.save(addressBook);

        return R.success(addressBook);

    }

    /**
     * 设置默认地址
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    private R<AddressBook> setDefault(@RequestBody AddressBook addressBook){

        //将原来的默认地址取消
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,BaseContextUtil.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault,1);

        AddressBook one = addressBookService.getOne(queryWrapper);
        if (one != null){
            one.setIsDefault(0);
            addressBookService.updateById(one);
        }

        //设置新的默认地址
        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);

        return R.success(addressBook);
    }

    /**
     * 查询默认地址
     * @return
     */
    @GetMapping("/default")
    @Cacheable
    public R<AddressBook> getDefault(){

        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,BaseContextUtil.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault,1);


        AddressBook addressBook = addressBookService.getOne(queryWrapper);

        if(addressBook == null){
            return R.error("未设置默认地址");
        }else {
            return R.success(addressBook);
        }

    }

    /**
     * 查询当前用户的所有的地址簿
     * @param addressBook
     * @return
     */
    @GetMapping("/list")
    @Cacheable
    public R<List<AddressBook>> list(AddressBook addressBook){

        addressBook.setUserId(BaseContextUtil.getCurrentId());

        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(addressBook.getUserId() != null,AddressBook::getUserId,addressBook.getUserId());
        queryWrapper.orderByAsc(AddressBook::getUpdateTime);

        //执行查询
        return R.success(addressBookService.list(queryWrapper));

    }



}
