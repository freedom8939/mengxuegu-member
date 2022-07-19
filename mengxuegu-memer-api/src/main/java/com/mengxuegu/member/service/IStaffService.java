package com.mengxuegu.member.service;

import com.mengxuegu.member.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.member.entity.req.PasswordREQ;
import com.mengxuegu.member.entity.req.StaffREQ;
import com.mengxuegu.member.base.Result;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
public interface IStaffService extends IService<Staff> {

    Result search(long page, long size, StaffREQ req);

    Result add(Staff staff);

    Result update(int id, Staff staff);

    /**
     * 检验原密码是否正确
     * @param req
     * @return
     */
    Result checkPassword(PasswordREQ req);

    /**
     * 更新新密码
     * @param req
     * @return
     */
    Result updatePassword(PasswordREQ req);

    Result login(String username,String password);

    /**
     * 通过token 获取用户信息
     * @param token
     * @return
     */
    Result getUserInfo(String token);
}
