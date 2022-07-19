package com.mengxuegu.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengxuegu.member.entity.Goods;
import com.mengxuegu.member.entity.Supplier;
import com.mengxuegu.member.entity.req.GoodsREQ;
import com.mengxuegu.member.mapper.GoodsMapper;
import com.mengxuegu.member.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.member.service.ISupplierService;
import com.mengxuegu.member.base.Page;
import com.mengxuegu.member.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public List<Goods> selectBySupplierId(int supplierId) {
        QueryWrapper<Goods> query = new QueryWrapper<>();
        query.eq("supplier_id", supplierId);
        return baseMapper.selectList(query);
    }

    @Override
    public Result search(long page, long size, GoodsREQ req) {
        if (req == null) {
            req = new GoodsREQ();
        }

        // 在GoodsMapper 已经实现了 searchPage 分页条件查询
        IPage<Goods> data = baseMapper.searchPage(new Page<Goods>(page, size),
                req);
        return Result.ok(data);
    }

    @Autowired
    private ISupplierService supplierService;

    @Override
    public Result findById(int id) {
        // 1. 查询商品详情
        Goods goods = baseMapper.selectById(id);
        if (goods != null && goods.getSupplierId() != null) {
            // 2.通过供应商id查询商品名称
            Supplier supplier = supplierService.getById(goods.getSupplierId());
            if (supplier != null) {
                goods.setSupplierName(supplier.getName());
            }
        }
        return Result.ok(goods);
    }

    @Override
    public Result update(int id, Goods goods) {
        if (goods.getId() == null) {
            goods.setId(id);
        }
        int i = baseMapper.updateById(goods);

        if (i < 1) {
            return Result.error("修改商品信息失败");
        }
        return Result.ok();
    }
}
