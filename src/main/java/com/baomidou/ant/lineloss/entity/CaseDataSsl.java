package com.baomidou.ant.lineloss.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 负荷数据
 * </p>
 *
 * @author jobob
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_DATA_SSL")
public class CaseDataSsl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("METERID")
    private String meterid;

    @TableField("DATA_TIME")
    private LocalDateTime dataTime;

    @TableField("V_A")
    private BigDecimal vA;

    @TableField("V_B")
    private BigDecimal vB;

    @TableField("V_C")
    private BigDecimal vC;

    @TableField("I_A")
    private BigDecimal iA;

    @TableField("I_B")
    private BigDecimal iB;

    @TableField("I_C")
    private BigDecimal iC;

    @TableField("P")
    private BigDecimal p;

    @TableField("P_A")
    private BigDecimal pA;

    @TableField("P_B")
    private BigDecimal pB;

    @TableField("P_C")
    private BigDecimal pC;

    @TableField("Q")
    private BigDecimal q;

    @TableField("Q_A")
    private BigDecimal qA;

    @TableField("Q_B")
    private BigDecimal qB;

    @TableField("Q_C")
    private BigDecimal qC;

    @TableField("PF")
    private BigDecimal pf;

    @TableField("PF_A")
    private BigDecimal pfA;

    @TableField("PF_B")
    private BigDecimal pfB;

    @TableField("PF_C")
    private BigDecimal pfC;

    @TableField("CASEID")
    private String caseid;


}
