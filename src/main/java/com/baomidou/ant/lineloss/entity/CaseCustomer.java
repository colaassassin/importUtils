package com.baomidou.ant.lineloss.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 案例客户表
 * </p>
 *
 * @author jobob
 * @since 2021-09-03
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
