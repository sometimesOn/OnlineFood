package com.jia.bulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jia.bulu.entity.Category;

public interface CategoryService extends IService<Category> {


    public void remove(Long id);

}
