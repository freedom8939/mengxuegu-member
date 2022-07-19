package com.mengxuegu.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.member.entity.Member;
import com.mengxuegu.member.mapper.MemberMapper;
import com.mengxuegu.member.entity.req.MemberREQ;
import com.mengxuegu.member.service.IMemberService;
import com.mengxuegu.member.base.Page;
import com.mengxuegu.member.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl
        extends ServiceImpl<MemberMapper, Member> implements IMemberService {


    @Override
    public Result search(long page, long size, MemberREQ req) {
        QueryWrapper query = new QueryWrapper<>();

        if (req != null) {
            if (StringUtils.isNotBlank(req.getName())) {
                query.like("name", req.getName());
            }
            if(StringUtils.isNotBlank(req.getCardNum())){
                query.like("card_num", req.getCardNum());

            }
           if(req.getBirthday() != null){
               query.eq("birthday",req.getBirthday());
           }
           if(StringUtils.isNotBlank(req.getPayType())){
               query.eq("pay_type",req.getPayType());
           }
        }
        // 封装分页对象
        IPage<Member> p = new Page<>(page, size);

        IPage<Member> data = baseMapper.selectPage(p, query);


        return Result.ok(data);
    }

    @Override
    public Result update(int id, Member member) {
        if(member.getId() == null){
            member.setId(id);
        }
        int i = baseMapper.updateById(member);
        if( i < 1) {
            return Result.error("修改会员信息失败");
        }else{
            return Result.ok();
        }
    }
}
