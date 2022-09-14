package com.jia.bulu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jia.bulu.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    /**
     * 更新status
     * @param id
     * @param status
     * @return
     */
    @Update("update dish set status = #{status} where id = #{id}")
    public boolean updateStatus(Long id,Integer status);

}
