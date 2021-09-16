package com.example.demo.ac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 案例客户表
 * </p>
 *
 * @author hsk
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_CUSTOMER")
public class CaseCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CUSTMERID")
    private String custmerid;

    @TableField("CONS_NO")
    private String consNo;

    @TableField("CUSTMER_NAME")
    private String custmerName;

    @TableField("CUSTMER_ADDR")
    private String custmerAddr;

    @TableField("TRANSFORMERID")
    private String transformerid;

    @TableField("ORGID")
    private String orgid;

    @TableField("CASEID")
    private String caseid;


}
