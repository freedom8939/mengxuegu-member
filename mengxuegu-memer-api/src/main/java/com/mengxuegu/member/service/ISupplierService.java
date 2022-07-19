package com.mengxuegu.member.service;

import com.mengxuegu.member.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.member.entity.req.SupplierREQ;
import com.mengxuegu.member.base.Result;

/**
 * <p>
 * 供应商信息表 服务类
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
public interface ISupplierService extends IService<Supplier> {

    /**
     * 分页条件查询
     * @param page 当前页码
     * @param size 每一页查询多少条
     * @param req 请求条件参数
     * @return
     */
    Result search(long page, long size, SupplierREQ req);

    /**
     * 供应商id
     * @return
     */
    Result deleteById(int id);


    /**
     * 更新数据
     * @param id
     * @param supplier
     * @return
     */
    Result update(int id,Supplier supplier);

}
