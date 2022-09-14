package com.jia.bulu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SetmealMapper extends BaseMapper<com.jia.bulu.entity.Setmeal> {

    /**
     * 更新套餐状态信息
     * @param status
     * @param id
     * @return
     */
    @Update("update setmeal set status = #{status} where id = #{id}")
    public boolean updateStatus(Integer status , Long id);

}
