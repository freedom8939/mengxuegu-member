package com.mengxuegu.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.member.entity.Member;
import com.mengxuegu.member.entity.req.MemberREQ;
import com.mengxuegu.member.base.Result;

public interface IMemberService extends IService<Member> {

    /**
     * 分页条件查询
     * @param req   查询条件
     * @return
     */
    Result search(long page, long size,MemberREQ req);

    Result update(int id, Member member);
}
