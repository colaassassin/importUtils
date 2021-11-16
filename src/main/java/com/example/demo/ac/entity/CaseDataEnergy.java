package com.example.demo.ac.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_DATA_ENERGY")
public class CaseDataEnergy implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("METERID")
    private String meterid;

    @TableField("BEGIN_TIME")
    private Date begintime;

    @TableField("END_TIME")
    private Date endtime;

    @TableField("CYCLE")
    private Integer cycle;

    /**
     * 计量方向
     1：正向
     2：反向
     */
    @TableField("DIRECTION")
    private Integer direction;

    @TableField("Z_DL")
    private BigDecimal zBm;

    @TableField("J_DL")
    private BigDecimal jBm;

    @TableField("F_DL")
    private BigDecimal fBm;

    @TableField("P_DL")
    private BigDecimal pBm;

    @TableField("G_DL")
    private BigDecimal gBm;

    @TableField("Q_DL")
    private BigDecimal qBm;

    @TableField("CASEID")
    private String caseid;
}
