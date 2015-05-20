package com.netcracker.router.annotation;

import com.netcracker.router.ContentType;
import com.netcracker.router.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kyrylo Berehovyi
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action {
    String action();
    HttpMethod httpMethod() default HttpMethod.GET;
    ContentType responseContentType() default ContentType.HTML;
}
