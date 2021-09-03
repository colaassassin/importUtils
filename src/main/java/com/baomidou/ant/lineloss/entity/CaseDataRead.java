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
 * 案例电能数据
 * </p>
 *
 * @author jobob
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_DATA_READ")
public class CaseDataRead implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("METERID")
    private String meterid;

    @TableField("DATA_TIME")
    private LocalDateTime dataTime;

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
