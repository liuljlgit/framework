package com.cloud.frame.securityvalidatecode.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 校验码类型
 * @author liulijun
 */
public enum ValidateCodeType {

    SMS((byte)1,"短信验证码"),

    IMAGE((byte)2,"图片验证码");

    private Byte code;

    private String desc;

    public static Map<Byte, String> codeBoolMap = new HashMap<>();

    public static Map<String, Byte> boolCodeMap = new HashMap<>();

    static {
        Arrays.stream(ValidateCodeType.values()).forEach(e -> codeBoolMap.put(e.getCode(), e.getDesc()));
        Arrays.stream(ValidateCodeType.values()).forEach(e -> boolCodeMap.put(e.getDesc(), e.getCode()));
    }

    ValidateCodeType(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
