package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sky.result.Result;
import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping()
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("套餐套餐信息:{}", setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    //分页查询套餐列表
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    //删除套餐
    @DeleteMapping()
    public Result delete(@RequestParam List<Long> ids){
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    //根据套餐id查询套餐详情
    @GetMapping("/{id}")
    public Result<SetmealVO> detail(@PathVariable Long id){
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }

    //修改套餐
    @PutMapping()
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("套餐套餐信息:{}", setmealDTO);
        setmealService.updateWithDish(setmealDTO);
        return Result.success();
    }
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, @RequestParam Long id) {
        log.info("套餐启售停售：{}", status);
        setmealService.updateStatus(status, id);
        return Result.success();
    }
}
