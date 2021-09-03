package com.baomidou.ant.lineloss.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 电能表
 * </p>
 *
 * @author jobob
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CASE_DEVICE_METER")
public class CaseDeviceMeter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 电能表id
     */
    @TableId("METERID")
    private String meterid;

    /**
     * 电能表名称
     */
    @TableField("METER_NAME")
    private String meterName;

    /**
     * 资产编号
     */
    @TableField("ASSET_CODE")
    private String assetCode;

    /**
     * 表计类型
     */
    @TableField("METER_TYPE")
    private Integer meterType;

    /**
     * 客户id
     */
    @TableField("CUSTMERID")
    private String custmerid;

    /**
     * 制造商
     */
    @TableField("MANUFACTURER")
    private Integer manufacturer;

    /**
     * 安装位置
     */
    @TableField("POSITION")
    private Integer position;

    /**
     * 电流互感器
     */
    @TableField("CTID")
    private Integer ctid;

    /**
     * 电压互感器
     */
    @TableField("PTID")
    private Integer ptid;

    /**
     * 综合变比
     */
    @TableField("RATE")
    private Integer rate;

    /**
     * 装表方向
     */
    @TableField("DIREACTION")
    private Integer direaction;

    /**
     * 电压等级
     */
    @TableField("VOLTAGE_CLASSID")
    private Integer voltageClassid;

    /**
     * 表号
     */
    @TableField("METER_CODE")
    private String meterCode;

    /**
     * 通信地址
     */
    @TableField("METER_ADDRESS")
    private String meterAddress;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 通信协议
     */
    @TableField("PROTOCOL")
    private Integer protocol;

    /**
     * 电压规格
     */
    @TableField("VOLTAGE_RATE")
    private Integer voltageRate;

    /**
     * 电流规格
     */
    @TableField("CURRENT_RATE")
    private Integer currentRate;

    /**
     * 型号
     */
    @TableField("MODEL")
    private Integer model;

    /**
     * 组织机构
     */
    @TableField("ORGID")
    private String orgid;

    /**
     * 接线方式
     */
    @TableField("WIRE_TYPE")
    private Integer wireType;

    /**
     * 开关状态
     */
    @TableField("SWITCH_STATU")
    private Integer switchStatu;

    /**
     * 台区id
     */
    @TableField("TRANSFORMERID")
    private String transformerid;

    /**
     * 计量点性质
     */
    @TableField("MAP_ATTR_CODE")
    private Integer mapAttrCode;

    /**
     * 计量点主用途
     */
    @TableField("USAGE_TYPE_CODE")
    private Integer usageTypeCode;

    @TableField("CASEID")
    private String caseid;


}
