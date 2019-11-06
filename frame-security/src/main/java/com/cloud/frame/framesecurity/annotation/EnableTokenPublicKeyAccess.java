package com.cloud.frame.framesecurity.annotation;

import com.cloud.frame.framesecurity.annotation.impl.TokenPublicKeyAccessImpl;
import org.springframework.context.annotation.Import;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(TokenPublicKeyAccessImpl.class)
public @interface EnableTokenPublicKeyAccess {
}
