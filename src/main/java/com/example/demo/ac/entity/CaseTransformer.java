package com.example.demo.ac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("CASE_TRANSFORMER")
public class CaseTransformer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("TRANSFORMERID")
    private String transformerid;

    @TableField("TRANSFORME_CODE")
    private String transformeCode;

    @TableField("TRANSFORME_NAME")
    private String transformeName;

    @TableField("TRANSFORME_CAPACITY")
    private String transformeCapacity;

    /**
     * 台区经理的名字
     */
    @TableField("MANAGER")
    private String manager;

    /**
     * 所属小区
     */
    @TableField("COMMUNITYID")
    private String communityid;

    /**
     * 所属机构
     */
    @TableField("ORGID")
    private String orgid;

    @TableField("TRANSFORMER_INDEX")
    private BigDecimal transformerIndex;

    @TableField("DISTRBU_GENERATION")
    private Integer distrbuGeneration;

    @TableField("CASEID")
    private String caseid;


}
