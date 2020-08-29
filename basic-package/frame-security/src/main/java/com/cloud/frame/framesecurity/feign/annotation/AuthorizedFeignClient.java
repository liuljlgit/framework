package com.cloud.frame.framesecurity.feign.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(AuthorizedFeignClientImpl.class)
public @interface AuthorizedFeignClient {

}
