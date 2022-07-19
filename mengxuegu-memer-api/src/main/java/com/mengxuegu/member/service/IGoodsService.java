package com.mengxuegu.member.service;

import com.mengxuegu.member.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.member.entity.req.GoodsREQ;
import com.mengxuegu.member.base.Result;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
public interface IGoodsService extends IService<Goods> {
    /**
     * 通过供应商Id 查询商品信息
     * @param supplierId
     * @return
     */
    List<Goods> selectBySupplierId(int supplierId);

//    IPage<Goods> searchPage(IPage<Goods> page, @Param("req") GoodsREQ req);
    Result search(long page, long size , GoodsREQ req);

    /**
     * 查询商品详情及供应商名称
     * @param id
     * @return
     */
    Result findById(int id);


    Result update(int id, Goods goods);
}
