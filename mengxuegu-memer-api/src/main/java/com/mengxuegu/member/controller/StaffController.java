package com.mengxuegu.member.controller;


import com.mengxuegu.member.entity.Staff;
import com.mengxuegu.member.entity.req.StaffREQ;
import com.mengxuegu.member.service.IStaffService;
import com.mengxuegu.member.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    /**
     * 分页条件查询
     * localhost:7777/list/search/1/20
     *
     * @param page
     * @param size
     * @param req
     * @return
     */
    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,
                         @PathVariable("size") long size,
                         @RequestBody(required = false) StaffREQ req) {
        return staffService.search(page, size, req);
    }

    @PostMapping
    public Result add( @RequestBody Staff staff) {
        return staffService.add(staff);
    }

    /**
     * localhost:7777/staff/6
     * 删除员工信息
     * @param id    员工id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")int id){
        boolean b = staffService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.error("删除员工信息失败");
    }


    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id){
        Staff staff = staffService.getById(id);
        return Result.ok(staff);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") int id
            ,@RequestBody Staff staff){
        return staffService.update(id,staff);
    }



}
