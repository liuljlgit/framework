package com.cloud.frame.securityvalidatecode;

import com.cloud.frame.framesecurity.validcode.entity.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 * @author liulijun
 */
public interface IValidateCodeGenerator {

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);

}
