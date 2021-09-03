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
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-09-03
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
    private LocalDateTime datatime;

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
