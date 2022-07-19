package com.mengxuegu.member.entity.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierREQ implements Serializable {

    private static final long serialVersionUID = 704887896869638064L;

    private String name;
    private String linkman;
    private String mobile;


}
