package com.mengxuegu.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mengxuegu.member.entity.Staff;
import com.mengxuegu.member.entity.req.PasswordREQ;
import com.mengxuegu.member.entity.req.StaffREQ;
import com.mengxuegu.member.mapper.StaffMapper;
import com.mengxuegu.member.service.IStaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.member.base.Page;
import com.mengxuegu.member.base.Result;
import com.mengxuegu.member.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {


    @Override
    public Result search(long page, long size, StaffREQ req) {
        Page<Staff> p = new Page<Staff>(page, size);
        QueryWrapper<Staff> query = new QueryWrapper<>();
        if(req != null){
            if(StringUtils.isNotBlank(req.getUsername())){
                query.like("username",req.getUsername());
            }
            if(StringUtils.isNotBlank(req.getName())){
                query.like("name",req.getName());
            }
        }
        Page<Staff> data = baseMapper.selectPage(p, query);
        return Result.ok(data);
    }

    @Override
    public Result add(Staff staff) {
        if(staff == null || StringUtils.isBlank(staff.getUsername())){
            return Result.error("用户名不能为空");
        }
        // 1.校验提交的账号是否存在
        Staff s = getByUsername(staff.getUsername());
        if(s != null){
            return Result.error("用户名已存在");
        }
        //2.加密密码
        String password =  new BCryptPasswordEncoder().encode(staff.getPassword());
        staff.setPassword(password);
        //3.提交数据
        boolean b = this.save(staff);
        if(b){
            return Result.ok();
        }
        return Result.error("新增员工信息失败");
    }

    @Override
    public Result update(int id, Staff staff) {
        if(staff.getId() == null){
            staff.setId(id);
        }
        //更新操作
        int i = baseMapper.updateById(staff);
        if(i<1){
            return Result.error("修改员工信息失败");
        }
        return Result.ok();
    }

    @Override
    public Result checkPassword(PasswordREQ req) {
        if(req == null || StringUtils.isEmpty(req.getPassword())){
            return Result.error("原密码不能为空");
        }
        //通过用户id查询用户信息 （包含正确密码）
        Staff staff = baseMapper.selectById(req.getUserId());
        //核对密码是否一致
        boolean b = new BCryptPasswordEncoder().matches(req.getPassword(),
                staff.getPassword());
        //判断输入的密码是否正确
        if( b ){
            return Result.ok();
        }
        return Result.error("原密码错误");
    }

    @Override
    public Result updatePassword(PasswordREQ req) {
        if(req ==null || StringUtils.isEmpty(req.getPassword())){
            return Result.error("新密码不能为空");
        }
        //将新密码加密
        String password
                = new BCryptPasswordEncoder().encode(req.getPassword());
        Staff staff = baseMapper.selectById(req.getUserId());
        staff.setPassword(password);
        baseMapper.updateById(staff);
        return Result.ok();
    }


    @Autowired
     JwtUtil jwtUtil;

    @Override
    public Result login(String username, String password) {
        Result error = Result.error("用户名或密码错误");

        //校验用户名和密码
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return error;
        }
        // 1.通过用户名查询数据
        Staff staff = getByUsername(username);
        //用户不存在
        if(staff == null){
            return error;
        }
        // 2. 用户存在
        boolean b = new BCryptPasswordEncoder().matches(password, staff.getPassword());
        if(!b){
            return error;
        }
        // 3.正确的情况下 生成token给客户端
       String jwt =
        jwtUtil.createJWT(staff.getId()+"",staff.getUsername(), true);
        // 4. 响应给客户端
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        return Result.ok(map);
    }

    @Override
    public Result getUserInfo(String token) {
        //解析令牌
        Claims claims = jwtUtil.parseJWT(token);
        //解析失败 或者用户名为空的时候
        if(claims == null ||
                StringUtils.isBlank(claims.getSubject())){
            return Result.error("用户令牌获取失败");
        }
        // 通过解析令牌 获取的 username 去查询对象
        String username = claims.getSubject();
        Staff staff = getByUsername(username);
        if(staff == null){
            return Result.error("用户不存在");
        }
        // 将密码进行处理，并将密码设置为空
        staff.setPassword("我的空密码");
        return Result.ok(staff);
    }

    public Staff getByUsername(String username){
        QueryWrapper<Staff> query = new QueryWrapper<>();
        query.eq("username",username);
        return baseMapper.selectOne(query);
    }
}
