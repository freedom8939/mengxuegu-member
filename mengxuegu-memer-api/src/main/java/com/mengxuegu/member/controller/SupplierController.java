package com.mengxuegu.member.controller;


import com.mengxuegu.member.entity.Supplier;
import com.mengxuegu.member.entity.req.SupplierREQ;
import com.mengxuegu.member.service.ISupplierService;
import com.mengxuegu.member.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 供应商信息表 前端控制器
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    /**
     * 分页条件查询供应商列表
     *
     * @param page
     * @param size
     * @param req
     * @return
     */
    @PostMapping("/list/search/{page}/{size}")
        public Result search(@PathVariable("page") int page,
                             @PathVariable("size") int size,
                             @RequestBody(required = false) SupplierREQ req) {
        return supplierService.search(page, size, req);
    }

    /**
     * 新增供应商
     *
     * @param supplier
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Supplier supplier) {
        boolean save = supplierService.save(supplier);
        if (save) {
            return Result.ok();
        } else {
            return Result.error("新增供应商失败");
        }
    }

    /**
     * 通过供应商id删除数据
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id) {
        return supplierService.deleteById(id);
    }

    /**
     * 根据供应商id查询信息 详情
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id) {
        Supplier supplier = supplierService.getById(id);
        return Result.ok(supplier);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") int id,
                         @RequestBody Supplier supplier){
        return supplierService.update(id, supplier);
    }
}
