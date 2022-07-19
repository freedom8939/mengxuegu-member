package com.mengxuegu.member.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengxuegu.member.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengxuegu.member.entity.req.GoodsREQ;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 分页查询商品列表
     * 1. 第一个参数传递分页对象 Page, (此对象封装当前页码， 还有每页查询多少条数据）
     * 2. 查询条件 @Param 取别名
     */
    IPage<Goods> searchPage(IPage<Goods> page, @Param("req")GoodsREQ req);

}
