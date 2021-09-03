package com.example.demo.utils4.helper.PoJo;

import com.example.demo.utils4.helper.annotation.InputColumnFormat;

import java.util.Date;

public class Qt {
    @InputColumnFormat(title = "TG_ID")
    private Long TG_ID;
    @InputColumnFormat(title = "ORG_NO")
    private Long ORG_NO;
    @InputColumnFormat(title = "TG_NO")
    private String TG_NO;
    @InputColumnFormat(title = "TG_NAME")
    private String TG_NAME;
    @InputColumnFormat(title = "TG_CAP")
    private Integer TG_CAP;
    @InputColumnFormat(title = "INST_ADDR")
    private String INST_ADDR;
    @InputColumnFormat(title = "CHG_DATE")
    private Date CHG_DATE;
    @InputColumnFormat(title = "PUB_PRIV_FLAG")
    private String PUB_PRIV_FLAG;
    @InputColumnFormat(title = "RUN_STATUS_CODE")
    private String RUN_STATUS_CODE;
    @InputColumnFormat(title = "SYS_USER_NO")
    private String SYS_USER_NO;

    public Date getCHG_DATE() {
        return CHG_DATE;
    }

    public void setCHG_DATE(Date CHG_DATE) {
        this.CHG_DATE = CHG_DATE;
    }

    public Date getHANDL_DATE() {
        return HANDL_DATE;
    }

    public void setHANDL_DATE(Date HANDL_DATE) {
        this.HANDL_DATE = HANDL_DATE;
    }

    @InputColumnFormat(title = "HANDL_DATE")
    private Date HANDL_DATE;
    @InputColumnFormat(title = "MNEMONIC")
    private String MNEMONIC;
    @InputColumnFormat(title = "TG_ID_MIS")
    private Long TG_ID_MIS;
    @InputColumnFormat(title = "TG_NO_MIS")
    private String TG_NO_MIS;
    @InputColumnFormat(title = "AVAILABLECAP")
    private String AVAILABLECAP;


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

    public String getTG_NO() {
        return TG_NO;
    }

    public void setTG_NO(String TG_NO) {
        this.TG_NO = TG_NO;
    }

    public String getTG_NAME() {
        return TG_NAME;
    }

    public void setTG_NAME(String TG_NAME) {
        this.TG_NAME = TG_NAME;
    }

    public Integer getTG_CAP() {
        return TG_CAP;
    }

    public void setTG_CAP(Integer TG_CAP) {
        this.TG_CAP = TG_CAP;
    }

    public String getINST_ADDR() {
        return INST_ADDR;
    }

    public void setINST_ADDR(String INST_ADDR) {
        this.INST_ADDR = INST_ADDR;
    }



    public String getPUB_PRIV_FLAG() {
        return PUB_PRIV_FLAG;
    }

    public void setPUB_PRIV_FLAG(String PUB_PRIV_FLAG) {
        this.PUB_PRIV_FLAG = PUB_PRIV_FLAG;
    }

    public String getRUN_STATUS_CODE() {
        return RUN_STATUS_CODE;
    }

    public void setRUN_STATUS_CODE(String RUN_STATUS_CODE) {
        this.RUN_STATUS_CODE = RUN_STATUS_CODE;
    }

    public String getSYS_USER_NO() {
        return SYS_USER_NO;
    }

    public void setSYS_USER_NO(String SYS_USER_NO) {
        this.SYS_USER_NO = SYS_USER_NO;
    }



    public String getMNEMONIC() {
        return MNEMONIC;
    }

    public void setMNEMONIC(String MNEMONIC) {
        this.MNEMONIC = MNEMONIC;
    }

    public Long getTG_ID_MIS() {
        return TG_ID_MIS;
    }

    public void setTG_ID_MIS(Long TG_ID_MIS) {
        this.TG_ID_MIS = TG_ID_MIS;
    }

    public String getTG_NO_MIS() {
        return TG_NO_MIS;
    }

    public void setTG_NO_MIS(String TG_NO_MIS) {
        this.TG_NO_MIS = TG_NO_MIS;
    }

    public String getAVAILABLECAP() {
        return AVAILABLECAP;
    }

    public void setAVAILABLECAP(String AVAILABLECAP) {
        this.AVAILABLECAP = AVAILABLECAP;
    }

    @Override
    public String toString() {
        return "Qt{" +
                "TG_ID=" + TG_ID +
                ", ORG_NO=" + ORG_NO +
                ", TG_NO='" + TG_NO + '\'' +
                ", TG_NAME='" + TG_NAME + '\'' +
                ", TG_CAP=" + TG_CAP +
                ", INST_ADDR='" + INST_ADDR + '\'' +
                ", CHG_DATE='" + CHG_DATE + '\'' +
                ", PUB_PRIV_FLAG='" + PUB_PRIV_FLAG + '\'' +
                ", RUN_STATUS_CODE='" + RUN_STATUS_CODE + '\'' +
                ", SYS_USER_NO='" + SYS_USER_NO + '\'' +
                ", HANDL_DATE='" + HANDL_DATE + '\'' +
                ", MNEMONIC='" + MNEMONIC + '\'' +
                ", TG_ID_MIS=" + TG_ID_MIS +
                ", TG_NO_MIS='" + TG_NO_MIS + '\'' +
                ", AVAILABLECAP='" + AVAILABLECAP + '\'' +
                '}';
    }
}
