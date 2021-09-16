package com.example.demo.ac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 案例清单
 * </p>
 *
 * @author hsk
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_LIST")
public class CaseList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CASEID")
    private String caseid;

    @TableField("CASE_NAME")
    private String caseName;

    @TableField("CASE_FAULT_CODE")
    private String caseFaultCode;

    @TableField("CASE_DESCRIB")
    private String caseDescrib;


}
