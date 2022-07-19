package com.mengxuegu.member.base;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ResultEnum {

    SUCCESS(2000, "成功"),
    ERROR(900, "失败");

    private Integer code;

    private String desc;
}
