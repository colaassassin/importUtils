package com.baomidou.ant.lineloss.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 案例终端/集中器
 * </p>
 *
 * @author jobob
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_DEVICE_RTU")
public class CaseDeviceRtu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 终端id
     */
    @TableId("RTUID")
    private String rtuid;

    /**
     * 终端名称
     */
    @TableField("RTU_NAME")
    private String rtuName;

    /**
     * 抄读方案
     */
    @TableField("READ_SCHEME")
    private String readScheme;

    /**
     * 通信地址
     */
    @TableField("RTU_ADDRESS")
    private String rtuAddress;

    @TableField("RTU_TYPE")
    private Integer rtuType;

    /**
     * 资产编号
     */
    @TableField("ASSET_CODE")
    private String assetCode;

    /**
     * 通信协议
     */
    @TableField("PROTOCOL")
    private Integer protocol;

    /**
     * 制造商
     */
    @TableField("MANUFACTURER")
    private Integer manufacturer;

    @TableField("POSITION")
    private String position;

    /**
     * 型号
     */
    @TableField("MODEL")
    private Integer model;

    /**
     * 上行通信方式
     */
    @TableField("UP_SIDE")
    private Integer upSide;

    /**
     * 下行通信方式
     */
    @TableField("DOWN_SIDE")
    private Integer downSide;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @TableField("ORGID")
    private String orgid;

    @TableField("CASEID")
    private String caseid;


}
