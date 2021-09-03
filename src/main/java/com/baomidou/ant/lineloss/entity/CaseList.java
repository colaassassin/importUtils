package com.baomidou.ant.lineloss.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 案例清单
 * </p>
 *
 * @author jobob
 * @since 2021-09-03
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
