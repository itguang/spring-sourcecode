package com.itguang.anno;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno {
    String value()  default "";
}
