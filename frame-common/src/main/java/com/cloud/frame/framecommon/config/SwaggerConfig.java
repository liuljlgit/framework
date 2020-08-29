package com.cloud.frame.framecommon.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(prefix = "swagger",name = "enable",havingValue = "true")
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.basePackage:}")
    private String basePackage;

    @Value("${swagger.groupName:文档服务}")
    private String groupName;

    @Value("${swagger.defaultLogin:false}")
    private Boolean defaultLogin;

    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        List<Parameter> pars = new ArrayList<Parameter>();
        if(defaultLogin){
            ParameterBuilder encryptStrPar = new ParameterBuilder();
            encryptStrPar.name("encryptStr").description("默认登录信息")
                    .modelRef(new ModelRef("string")).parameterType("header")
                    .required(false).defaultValue("NBFtHVZsv6zYH1kZaeCasuw7ORm5zjLX7wr3tbFRLPYHqC_x7T1bpZ0s9w0bmX8k").build();
            pars.add(encryptStrPar.build());
        }

        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Authorization").description("登录校验")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("Bearer ").build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .globalOperationParameters(pars)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloud"))
                .paths(PathSelectors.any())
                .build();
    }
}

