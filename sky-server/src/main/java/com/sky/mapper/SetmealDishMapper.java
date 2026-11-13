package com.sky.mapper;


import com.sky.entity.Dish;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {


    List<Long> getSetmealIdsByDishId(List<Long> dishIds);


    void insertBatch(List<SetmealDish> setmealDishes);



    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getSetmealId(Long setmealId);

    @Select("select * from dish where id in (select dish_id from setmeal_dish where setmeal_id = #{setmealId})")
    List<Dish> getBySetmealId(Long setmealId);
}
