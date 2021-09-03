package com.example.demo.utils3.PoJo;

import com.example.demo.utils3.annotation.ExcelField;
import lombok.Data;

import java.util.Date;

@Data
public class LineLossData {
    @ExcelField(title = "REC_ID")
    private Long REC_ID;
    @ExcelField(title = "PRO_ORG_NO")
    private Long PRO_ORG_NO;
    @ExcelField(title = "TG_ID")
    private Long TG_ID;
    @ExcelField(title = "ORG_NO")
    private Long ORG_NO;
    @ExcelField(title = "P_ORG_NO")
    private Long P_ORG_NO;
    @ExcelField(title = "TG_NO")
    private Long TG_NO;
    @ExcelField(title = "TG_NAME")
    private String TG_NAME;
    @ExcelField(title = "CONS_NO")
    private Long CONS_NO;
    @ExcelField(title = "CONS_NAME")
    private String CONS_NAME;
    @ExcelField(title = "PQ_TYPE")
    private Integer PQ_TYPE;
    @ExcelField(title = "STAT_DATE")
    private Date STAT_DATE;
    @ExcelField(title = "METER_ID")
    private Long METER_ID;
    @ExcelField(title = "METER_ASSET_NO")
    private Long METER_ASSET_NO;
    @ExcelField(title = "METER_BAR_CODE")
    private Integer METER_BAR_CODE;
    @ExcelField(title = "COLL_OBJ_ID")
    private Long COLL_OBJ_ID;
    @ExcelField(title = "MP_ID")
    private Long MP_ID;
    @ExcelField(title = "T_FACTOR")
    private Integer T_FACTOR;
    @ExcelField(title = "MP_LEVEL")
    private Integer MP_LEVEL;
    @ExcelField(title = "READ_TYPE_CODE")
    private Integer READ_TYPE_CODE;
    @ExcelField(title = "LAST_READ")
    private Double LAST_READ;
    @ExcelField(title = "THIS_READ")
    private Double THIS_READ;
    @ExcelField(title = "COLL_PQ")
    private Double COLL_PQ;
    @ExcelField(title = "ESTIMATE_PQ")
    private Double ESTIMATE_PQ;
    @ExcelField(title = "MR_PQ")
    private Double MR_PQ;
    @ExcelField(title = "ADJ_PQ")
    private Double ADJ_PQ;
    @ExcelField(title = "FIX_TYPEl")
    private Integer FIX_TYPEl;
    @ExcelField(title = "SP_ID")
    private Long SP_ID;
    @ExcelField(title = "DATA_QUALITY_FLAG")
    private Integer DATA_QUALITY_FLAG;
    @ExcelField(title = "ID_GROUP")
    private Long ID_GROUP;


}
