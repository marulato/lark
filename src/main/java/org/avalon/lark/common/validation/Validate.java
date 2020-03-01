package org.avalon.lark.common.validation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validate {
    String[] withMethods();
    String[] messages();
}
