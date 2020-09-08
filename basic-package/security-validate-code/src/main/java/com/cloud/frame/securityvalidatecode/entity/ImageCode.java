package com.cloud.frame.securityvalidatecode.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ImageCode")
public class ImageCode extends ValidateCode {

    @ApiModelProperty("图片")
    private BufferedImage image;

}
