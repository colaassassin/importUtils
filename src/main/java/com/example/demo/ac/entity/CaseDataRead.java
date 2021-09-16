package com.example.demo.ac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 案例电能数据
 * </p>
 *
 * @author hsk
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_DATA_READ")
public class CaseDataRead implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("METERID")
    private String meterid;

    @TableField("DATA_TIME")
    private Date dataTime;

    /**
     * 计量方向
1：正向
2：反向
     */
    @TableField("DIRECTION")
    private Integer direction;

    @TableField("Z_BM")
    private BigDecimal zBm;

    @TableField("J_BM")
    private BigDecimal jBm;

    @TableField("F_BM")
    private BigDecimal fBm;

    @TableField("P_BM")
    private BigDecimal pBm;

    @TableField("G_BM")
    private BigDecimal gBm;

    @TableField("Q_BM")
    private BigDecimal qBm;

    @TableField("CASEID")
    private String caseid;


}
