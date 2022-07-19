package com.mengxuegu.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengxuegu.member.entity.Goods;
import com.mengxuegu.member.entity.Supplier;
import com.mengxuegu.member.entity.req.SupplierREQ;
import com.mengxuegu.member.mapper.SupplierMapper;
import com.mengxuegu.member.service.IGoodsService;
import com.mengxuegu.member.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.member.base.Page;
import com.mengxuegu.member.base.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 供应商信息表 服务实现类
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Autowired
    private IGoodsService goodsService;

    @Override
    public Result search(long page, long size, SupplierREQ req) {
        IPage<Supplier> p = new Page<>(page, size);
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();

        if(req != null){
            if(StringUtils.isNotBlank(req.getName())){
                queryWrapper.like("name",req.getName());
            }
            if(StringUtils.isNotBlank(req.getLinkman())){
                queryWrapper.like("linkname",req.getLinkman());
            }
            if(StringUtils.isNotBlank(req.getMobile())){
                queryWrapper.like("mobile",req.getMobile());
            }
        }

        // 分页查询供应商
        IPage<Supplier> data = baseMapper.selectPage(p,queryWrapper);
        return Result.ok(data);
    }

    @Override
    public Result deleteById(int id) {
        // 1.通过供应商id查询是否被商品引用
        List<Goods> goodsList = goodsService.selectBySupplierId(id);
        // 2.如果被商品引用，则不让删除供应商
        if(CollectionUtils.isNotEmpty(goodsList)){
            return Result.error("该供应商被商品引用，不允许删除");
        }
        int i = baseMapper.deleteById(id);
        if(i < 1){
            //删除失败
            return Result.error("删除供应商失败");
        }
        return Result.ok();

    }

    @Override
    public Result update(int id, Supplier supplier) {
        if(supplier.getId() == null){
            supplier.setId(id);
        }
        int i = baseMapper.updateById(supplier);
        if( i < 1){
            return Result.error("修改供应商信息失败");
        }
        return Result.ok();
    }


}
