package com.mengxuegu.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员信息表
 */
@Data
@TableName("tb_member")
public class Member implements Serializable {


    private static final long serialVersionUID = 6455653748871493520L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String cardNum;

    private String name;

    private Date birthday;

    private String phone;

    private Integer integral;

    private Double money;

    private String payType;

    private String address;

}
