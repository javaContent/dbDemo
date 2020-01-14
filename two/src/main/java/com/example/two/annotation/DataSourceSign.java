package com.example.two.annotation;

import com.example.two.common.ContextConst;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceSign {
    ContextConst.DataSourceType value() default ContextConst.DataSourceType.PRIMARY;
}
