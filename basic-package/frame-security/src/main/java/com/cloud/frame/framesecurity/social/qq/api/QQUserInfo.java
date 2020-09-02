package com.cloud.frame.framesecurity.social.qq.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("从QQ接收到的用户信息字段映射到实体中")
public class QQUserInfo {

    @ApiModelProperty("返回码")
    private String ret;

    @ApiModelProperty("如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码")
    private String msg;

    @ApiModelProperty("openId")
    private String openId;

    @ApiModelProperty("is_lost")
    private String is_lost;

    @ApiModelProperty("省(直辖市)")
    private String province;

    @ApiModelProperty("市(直辖市区)")
    private String city;

    @ApiModelProperty("出生年月")
    private String year;

    @ApiModelProperty("用户在QQ空间的昵称")
    private String nickname;

    @ApiModelProperty("大小为30×30像素的QQ空间头像URL")
    private String figureurl;

    @ApiModelProperty("figureurl_type")
    private String figureurl_type;

    @ApiModelProperty("大小为50×50像素的QQ空间头像URL")
    private String figureurl_1;

    @ApiModelProperty("大小为100×100像素的QQ空间头像URL")
    private String figureurl_2;

    @ApiModelProperty("figureurl_qq")
    private String figureurl_qq;

    @ApiModelProperty("大小为40×40像素的QQ头像URL")
    private String figureurl_qq_1;

    @ApiModelProperty("大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100×100的头像，但40×40像素则是一定会有")
    private String figureurl_qq_2;

    @ApiModelProperty("性别。 如果获取不到则默认返回”男”")
    private String gender;

    @ApiModelProperty("标识用户是否为黄钻用户（0：不是；1：是）")
    private String is_yellow_vip;

    @ApiModelProperty("标识用户是否为黄钻用户（0：不是；1：是）")
    private String vip;

    @ApiModelProperty("黄钻等级")
    private String yellow_vip_level;

    @ApiModelProperty("黄钻等级")
    private String level;

    @ApiModelProperty("标识是否为年费黄钻用户（0：不是； 1：是）")
    private String is_yellow_year_vip;

    @ApiModelProperty("constellation")
    private String constellation;
}
