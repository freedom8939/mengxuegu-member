package com.mengxuegu.member.controller;


import com.mengxuegu.member.entity.Goods;
import com.mengxuegu.member.entity.req.GoodsREQ;
import com.mengxuegu.member.service.IGoodsService;
import com.mengxuegu.member.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,
                         @PathVariable("size") long size ,
                         @RequestBody(required = false) GoodsREQ req){
        return goodsService.search(page, size, req);
    }

    @PostMapping
    public Result ad(@RequestBody Goods goods){
        boolean b = goodsService.save(goods);
        if(b){
            //新增成功
            return Result.ok();
        }
        return Result.error("新增商品信息失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        boolean b = goodsService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.error("删除商品信息失败");
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id){
       return  goodsService.findById(id);
    }


    @PutMapping("/{id}")
     public Result update(@PathVariable("id") int id,
                          @RequestBody Goods goods){
        return goodsService.update(id, goods);
     }

}

