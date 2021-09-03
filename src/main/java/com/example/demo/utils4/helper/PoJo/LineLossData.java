package com.example.demo.utils4.helper.PoJo;

import com.example.demo.utils3.annotation.ExcelField;
import com.example.demo.utils4.helper.annotation.ColumnFormat;
import com.example.demo.utils4.helper.annotation.InputColumnFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


public class LineLossData {
    @InputColumnFormat(title = "REC_ID")
    private BigDecimal REC_ID;
    @InputColumnFormat(title = "PRO_ORG_NO")
    private Long PRO_ORG_NO;
    @InputColumnFormat(title = "TG_ID")
    private Long TG_ID;
    @InputColumnFormat(title = "ORG_NO")
    private Long ORG_NO;
    @InputColumnFormat(title = "P_ORG_NO")
    private Long P_ORG_NO;
    @InputColumnFormat(title = "TG_NO")
    private Long TG_NO;
    @InputColumnFormat(title = "TG_NAME")
    private String TG_NAME;
    @InputColumnFormat(title = "CONS_NO")
    private Long CONS_NO;
    @InputColumnFormat(title = "CONS_NAME")
    private String CONS_NAME;
    @InputColumnFormat(title = "PQ_TYPE")
    private Integer PQ_TYPE;
    @InputColumnFormat(title = "STAT_DATE")
    private Date STAT_DATE;
    @InputColumnFormat(title = "METER_ID")
    private BigDecimal METER_ID;
    @InputColumnFormat(title = "METER_ASSET_NO")
    private BigDecimal METER_ASSET_NO;
    @InputColumnFormat(title = "METER_BAR_CODE")
    private Integer METER_BAR_CODE;
    @InputColumnFormat(title = "COLL_OBJ_ID")
    private Long COLL_OBJ_ID;
    @InputColumnFormat(title = "MP_ID")
    private Long MP_ID;
    @InputColumnFormat(title = "T_FACTOR")
    private Integer T_FACTOR;
    @InputColumnFormat(title = "MP_LEVEL")
    private Integer MP_LEVEL;
    @InputColumnFormat(title = "READ_TYPE_CODE")
    private Integer READ_TYPE_CODE;
    @InputColumnFormat(title = "LAST_READ")
    private Double LAST_READ;
    @InputColumnFormat(title = "THIS_READ")
    private Double THIS_READ;
    @InputColumnFormat(title = "COLL_PQ")
    private Double COLL_PQ;
    @InputColumnFormat(title = "ESTIMATE_PQ")
    private Double ESTIMATE_PQ;
    @InputColumnFormat(title = "MR_PQ")
    private Double MR_PQ;
    @InputColumnFormat(title = "ADJ_PQ")
    private Double ADJ_PQ;
    @InputColumnFormat(title = "FIX_TYPE")
    private Integer FIX_TYPEl;
    @InputColumnFormat(title = "SP_ID")
    private Long SP_ID;
    @InputColumnFormat(title = "DATA_QUALITY_FLAG")
    private Integer DATA_QUALITY_FLAG;
    @InputColumnFormat(title = "ID_GROUP")
    private Long ID_GROUP;



    public Long getPRO_ORG_NO() {
        return PRO_ORG_NO;
    }

    public void setPRO_ORG_NO(Long PRO_ORG_NO) {
        this.PRO_ORG_NO = PRO_ORG_NO;
    }

    public Long getTG_ID() {
        return TG_ID;
    }

    public void setTG_ID(Long TG_ID) {
        this.TG_ID = TG_ID;
    }

    public Long getORG_NO() {
        return ORG_NO;
    }

    public void setORG_NO(Long ORG_NO) {
        this.ORG_NO = ORG_NO;
    }

    public Long getP_ORG_NO() {
        return P_ORG_NO;
    }

    public void setP_ORG_NO(Long p_ORG_NO) {
        P_ORG_NO = p_ORG_NO;
    }

    public Long getTG_NO() {
        return TG_NO;
    }

    public void setTG_NO(Long TG_NO) {
        this.TG_NO = TG_NO;
    }

    public String getTG_NAME() {
        return TG_NAME;
    }

    public void setTG_NAME(String TG_NAME) {
        this.TG_NAME = TG_NAME;
    }

    public Long getCONS_NO() {
        return CONS_NO;
    }

    public void setCONS_NO(Long CONS_NO) {
        this.CONS_NO = CONS_NO;
    }

    public String getCONS_NAME() {
        return CONS_NAME;
    }

    public void setCONS_NAME(String CONS_NAME) {
        this.CONS_NAME = CONS_NAME;
    }

    public Integer getPQ_TYPE() {
        return PQ_TYPE;
    }

    public void setPQ_TYPE(Integer PQ_TYPE) {
        this.PQ_TYPE = PQ_TYPE;
    }

    public Date getSTAT_DATE() {
        return STAT_DATE;
    }

    public void setSTAT_DATE(Date STAT_DATE) {
        this.STAT_DATE = STAT_DATE;
    }

    public BigDecimal getMETER_ID() {
        return METER_ID;
    }

    public void setMETER_ID(BigDecimal METER_ID) {
        this.METER_ID = METER_ID;
    }

    public BigDecimal getREC_ID() {
        return REC_ID;
    }

    public void setREC_ID(BigDecimal REC_ID) {
        this.REC_ID = REC_ID;
    }

    public BigDecimal getMETER_ASSET_NO() {
        return METER_ASSET_NO;
    }

    public void setMETER_ASSET_NO(BigDecimal METER_ASSET_NO) {
        this.METER_ASSET_NO = METER_ASSET_NO;
    }

    public Integer getMETER_BAR_CODE() {
        return METER_BAR_CODE;
    }

    public void setMETER_BAR_CODE(Integer METER_BAR_CODE) {
        this.METER_BAR_CODE = METER_BAR_CODE;
    }

    public Long getCOLL_OBJ_ID() {
        return COLL_OBJ_ID;
    }

    public void setCOLL_OBJ_ID(Long COLL_OBJ_ID) {
        this.COLL_OBJ_ID = COLL_OBJ_ID;
    }

    public Long getMP_ID() {
        return MP_ID;
    }

    public void setMP_ID(Long MP_ID) {
        this.MP_ID = MP_ID;
    }

    public Integer getT_FACTOR() {
        return T_FACTOR;
    }

    public void setT_FACTOR(Integer t_FACTOR) {
        T_FACTOR = t_FACTOR;
    }

    public Integer getMP_LEVEL() {
        return MP_LEVEL;
    }

    public void setMP_LEVEL(Integer MP_LEVEL) {
        this.MP_LEVEL = MP_LEVEL;
    }

    public Integer getREAD_TYPE_CODE() {
        return READ_TYPE_CODE;
    }

    public void setREAD_TYPE_CODE(Integer READ_TYPE_CODE) {
        this.READ_TYPE_CODE = READ_TYPE_CODE;
    }

    public Double getLAST_READ() {
        return LAST_READ;
    }

    public void setLAST_READ(Double LAST_READ) {
        this.LAST_READ = LAST_READ;
    }

    public Double getTHIS_READ() {
        return THIS_READ;
    }

    public void setTHIS_READ(Double THIS_READ) {
        this.THIS_READ = THIS_READ;
    }

    public Double getCOLL_PQ() {
        return COLL_PQ;
    }

    public void setCOLL_PQ(Double COLL_PQ) {
        this.COLL_PQ = COLL_PQ;
    }

    public Double getESTIMATE_PQ() {
        return ESTIMATE_PQ;
    }

    public void setESTIMATE_PQ(Double ESTIMATE_PQ) {
        this.ESTIMATE_PQ = ESTIMATE_PQ;
    }

    public Double getMR_PQ() {
        return MR_PQ;
    }

    public void setMR_PQ(Double MR_PQ) {
        this.MR_PQ = MR_PQ;
    }

    public Double getADJ_PQ() {
        return ADJ_PQ;
    }

    public void setADJ_PQ(Double ADJ_PQ) {
        this.ADJ_PQ = ADJ_PQ;
    }

    public Integer getFIX_TYPEl() {
        return FIX_TYPEl;
    }

    public void setFIX_TYPEl(Integer FIX_TYPEl) {
        this.FIX_TYPEl = FIX_TYPEl;
    }

    public Long getSP_ID() {
        return SP_ID;
    }

    public void setSP_ID(Long SP_ID) {
        this.SP_ID = SP_ID;
    }

    public Integer getDATA_QUALITY_FLAG() {
        return DATA_QUALITY_FLAG;
    }

    public void setDATA_QUALITY_FLAG(Integer DATA_QUALITY_FLAG) {
        this.DATA_QUALITY_FLAG = DATA_QUALITY_FLAG;
    }

    public Long getID_GROUP() {
        return ID_GROUP;
    }

    public void setID_GROUP(Long ID_GROUP) {
        this.ID_GROUP = ID_GROUP;
    }

    @Override
    public String toString() {
        return "LineLossData{" +
                "REC_ID=" + REC_ID +
                ", PRO_ORG_NO=" + PRO_ORG_NO +
                ", TG_ID=" + TG_ID +
                ", ORG_NO=" + ORG_NO +
                ", P_ORG_NO=" + P_ORG_NO +
                ", TG_NO=" + TG_NO +
                ", TG_NAME='" + TG_NAME + '\'' +
                ", CONS_NO=" + CONS_NO +
                ", CONS_NAME='" + CONS_NAME + '\'' +
                ", PQ_TYPE=" + PQ_TYPE +
                ", STAT_DATE=" + STAT_DATE +
                ", METER_ID=" + METER_ID +
                ", METER_ASSET_NO=" + METER_ASSET_NO +
                ", METER_BAR_CODE=" + METER_BAR_CODE +
                ", COLL_OBJ_ID=" + COLL_OBJ_ID +
                ", MP_ID=" + MP_ID +
                ", T_FACTOR=" + T_FACTOR +
                ", MP_LEVEL=" + MP_LEVEL +
                ", READ_TYPE_CODE=" + READ_TYPE_CODE +
                ", LAST_READ=" + LAST_READ +
                ", THIS_READ=" + THIS_READ +
                ", COLL_PQ=" + COLL_PQ +
                ", ESTIMATE_PQ=" + ESTIMATE_PQ +
                ", MR_PQ=" + MR_PQ +
                ", ADJ_PQ=" + ADJ_PQ +
                ", FIX_TYPEl=" + FIX_TYPEl +
                ", SP_ID=" + SP_ID +
                ", DATA_QUALITY_FLAG=" + DATA_QUALITY_FLAG +
                ", ID_GROUP=" + ID_GROUP +
                '}';
    }
}
