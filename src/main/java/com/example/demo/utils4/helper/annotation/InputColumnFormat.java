package com.example.demo.utils4.helper.annotation;

import com.example.demo.utils4.helper.deserializer.AbstractCellDeserializer;
import com.example.demo.utils4.helper.deserializer.impl.DefaultDeserializer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InputColumnFormat {

    String title() default "";

    Class<? extends AbstractCellDeserializer> deserializer() default DefaultDeserializer.class;

    String[] args() default {};
}
