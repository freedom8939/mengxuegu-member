package com.mengxuegu.member.entity.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsREQ implements Serializable {

    private static final long serialVersionUID = -4711171656055614690L;

    private String name;

    private String code;

    private Integer supplierId;
}
