package com.mengxuegu.member.entity.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordREQ implements Serializable {

    private static final long serialVersionUID = -3370999615503307517L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 原密码或者新密码
     */
    private String password;
}
