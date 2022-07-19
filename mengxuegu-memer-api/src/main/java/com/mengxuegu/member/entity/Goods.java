package com.mengxuegu.member.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author 蓝书签
 * @since 2022-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 商品规格
     */
    private String spec;

    /**
     * 零售价
     */
    private BigDecimal retailPrice;

    /**
     * 进货价
     */
    private BigDecimal purchasePrice;

    /**
     * 库存数量
     */
    private Integer storageNum;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 供应商名称
     */
    @TableField(exist = false)  //标识  不是tb_goods中的字段
    private String supplierName;

}
