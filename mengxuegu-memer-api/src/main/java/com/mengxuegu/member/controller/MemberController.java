package com.mengxuegu.member.controller;

import com.mengxuegu.member.entity.Member;
import com.mengxuegu.member.entity.req.MemberREQ;
import com.mengxuegu.member.service.IMemberService;
import com.mengxuegu.member.base.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IMemberService memberService;

    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,
                         @PathVariable("size") long size ,
                         @RequestBody MemberREQ req) {
        logger.info("查询会员列表：page= {}, size={}, req={}",page, size,
                req);

        return memberService.search(page,size,req);
    }

    //post 请求/member
    @PostMapping
    public Result add(@RequestBody Member member){
        System.out.println(member);
        boolean b = memberService.save(member);
        if(b) {
            return Result.ok();
        }else{
            return Result.error("新增会员信息失败");
        }
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id ){
        boolean b = memberService.removeById(id);
        if(b) {
            return Result.ok();
        }else{
            return Result.error("删除会员失败");
        }
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id) {
        Member member = memberService.getById(id);
        return Result.ok(member);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") int id,
                         @RequestBody Member member
                         ){
           return memberService.update(id, member);
    }
}
