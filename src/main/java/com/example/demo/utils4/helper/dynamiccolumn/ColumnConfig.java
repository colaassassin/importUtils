package com.example.demo.utils4.helper.dynamiccolumn;


import com.example.demo.utils4.helper.serializer.AbstractCellSerializer;
import com.example.demo.utils4.helper.serializer.impl.DefaultSerializer;
import com.example.demo.utils4.helper.type.CellDataType;


public class ColumnConfig {

    /**
     *  标题
     */
    private String title;

    /**
     *  单元格类型，仅针对excel，csv无格式
     */
    private CellDataType cellDataType = CellDataType.NONE;

    /**
     *  单元格序列化器
     */
    private AbstractCellSerializer cellSerializer =  new DefaultSerializer();

    /**
     * 序列化器参数数组
     */
    private String[] serializerArgs = new String[]{};

    public ColumnConfig(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCellDataType(CellDataType cellDataType) {
        this.cellDataType = cellDataType;
    }

    public CellDataType getCellDataType() {
        return cellDataType;
    }

    public AbstractCellSerializer getCellSerializer() {
        return cellSerializer;
    }

    public void setCellSerializer(AbstractCellSerializer cellSerializer) {
        this.cellSerializer = cellSerializer;
    }

    public String[] getSerializerArgs() {
        return serializerArgs;
    }

    public void setSerializerArgs(String []serializerArgs) {
        this.serializerArgs = serializerArgs;
    }

}
