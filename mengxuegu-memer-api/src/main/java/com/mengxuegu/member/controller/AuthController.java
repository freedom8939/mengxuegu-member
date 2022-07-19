package com.mengxuegu.member.controller;

import com.mengxuegu.member.entity.Staff;
import com.mengxuegu.member.entity.req.PasswordREQ;
import com.mengxuegu.member.service.IStaffService;
import com.mengxuegu.member.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private IStaffService staffService;

    /**
     * 校验原密码是否正确  localhost:7777/user/pwd
     * @param req
     * @return
     */
    @PostMapping("/pwd")
    public Result checkPwd(@RequestBody PasswordREQ req){
        return staffService.checkPassword(req);
    }

    /**
     * 提交修改密码
     * @param req
     * @return
     */
    @PutMapping("/pwd")
    public Result updatePwd(@RequestBody PasswordREQ req){
        return staffService.updatePassword(req);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Staff staff){
       return  staffService.login(staff.getUsername(),staff.getPassword());
    }

    /**
     * /user/info?token=xxx
     * 通过token 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/info")
    public Result getUserInfo(@RequestParam String token){
       return  staffService.getUserInfo(token);
    }


    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
