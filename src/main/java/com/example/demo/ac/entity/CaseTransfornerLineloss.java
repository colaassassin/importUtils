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
 * 
 * </p>
 *
 * @author hsk
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_TRANSFORNER_LINELOSS")
public class CaseTransfornerLineloss implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("TRANSFORMER_LOSS_ID")
    private String transformerLossId;

    @TableField("TRANSFORMERID")
    private String transformerid;

    @TableField("CYCLE")
    private Integer cycle;

    @TableField("DATATIME")
    private Date datatime;

    @TableField("SUPPLEY_ENERGY")
    private BigDecimal suppleyEnergy;

    @TableField("SALE_ENERGY")
    private BigDecimal saleEnergy;

    @TableField("LOSS_ENERGY")
    private BigDecimal lossEnergy;

    @TableField("LOSS_RATE")
    private BigDecimal lossRate;

    @TableField("CASEID")
    private String caseid;


}
