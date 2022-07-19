package com.mengxuegu.member.entity.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 接受会员模块查询条件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberREQ implements Serializable {

    private String cardNum;
    private String name;
    private String birthday;
    private String payType;

    private static final long serialVersionUID = 3412614241715062489L;

}
