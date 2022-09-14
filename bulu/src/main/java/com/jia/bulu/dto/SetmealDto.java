package com.jia.bulu.dto;


import com.jia.bulu.entity.Setmeal;
import com.jia.bulu.entity.SetmealDish;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishList = new ArrayList<>();

    private String categoryName;

}
