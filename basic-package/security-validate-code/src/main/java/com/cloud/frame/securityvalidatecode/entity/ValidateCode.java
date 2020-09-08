package com.cloud.frame.securityvalidatecode.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ValidateCode")
public class ValidateCode {

    @ApiModelProperty("校验码")
    private String code;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

}
