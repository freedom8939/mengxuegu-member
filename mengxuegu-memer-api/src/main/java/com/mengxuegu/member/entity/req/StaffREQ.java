package com.mengxuegu.member.entity.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class StaffREQ implements Serializable {

    private static final long serialVersionUID = -5379679497834828473L;

    private String username;

    private String name;
}
