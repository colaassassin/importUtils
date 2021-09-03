package com.example.demo.utils4.helper.annotation;

import com.example.demo.utils4.helper.serializer.AbstractCellSerializer;
import com.example.demo.utils4.helper.serializer.impl.DefaultSerializer;
import com.example.demo.utils4.helper.type.CellDataType;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnFormat {

    int NO_ORDER = -1;

    int order() default NO_ORDER;

    String title() default "";

    CellDataType cellType() default CellDataType.NONE;

    Class<? extends AbstractCellSerializer> serializer() default DefaultSerializer.class;

    String[] args() default {};

}
