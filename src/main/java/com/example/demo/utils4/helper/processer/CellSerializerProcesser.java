package com.example.demo.utils4.helper.processer;


import com.example.demo.utils4.helper.serializer.AbstractCellSerializer;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.util.CsvContext;


public class CellSerializerProcesser extends CellProcessorAdaptor {

    private AbstractCellSerializer cellSerializer;
    private String[] args;

    public CellSerializerProcesser(AbstractCellSerializer cellSerializer, String[] args) {
        this.cellSerializer = cellSerializer;
        this.args = args;
    }

    public Object execute(final Object value, final CsvContext context) {
        Object result = value == null ? null : cellSerializer.serialize(value, args);
        return next.execute(result, context);
    }
}
