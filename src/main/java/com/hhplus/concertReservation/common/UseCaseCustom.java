package com.hhplus.concertReservation.common;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component

public @interface UseCaseCustom {
    @AliasFor(annotation = Component.class)
    String value() default "";

}
