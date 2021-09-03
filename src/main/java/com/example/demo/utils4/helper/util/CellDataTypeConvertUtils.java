package com.example.demo.utils4.helper.util;


import com.example.demo.utils4.helper.type.CellDataType;
import org.apache.poi.ss.usermodel.CellType;


public class CellDataTypeConvertUtils {

    public static CellType getExcelCellTypeByCellType(CellDataType dataType) {
        switch (dataType) {
            case STRING :
                return CellType.STRING;
            case NUMERIC :
                return CellType.NUMERIC;
            case FORMULA :
                return CellType.NUMERIC;
            case BLANK :
                return CellType.BLANK;
            case BOOLEAN :
                return CellType.BOOLEAN;
            case ERROR :
                return CellType.ERROR;
            default :
                return CellType._NONE;
        }
    }
}
