package com.mengxuegu.member.base;


import java.util.List;


public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {


    public List<T> getRows(){
        return super.getRecords();
    }

    // 重置mp的page类
    @Override
    public List<T> getRecords(){
        return null;
    }

    public Page(long current, long size) {
        super(current, size);
    }
}
