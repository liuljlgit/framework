package com.cloud.frame.framecommon.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CommonStatus {
    NO((byte)0,"否"),
    YES((byte)1,"是");

    private Byte code;

    private String desc;

    public static Map<Byte, String> codeBoolMap = new HashMap<>();

    public static Map<String, Byte> boolCodeMap = new HashMap<>();

    static {
        Arrays.stream(CommonStatus.values()).forEach(e -> codeBoolMap.put(e.getCode(), e.getDesc()));
        Arrays.stream(CommonStatus.values()).forEach(e -> boolCodeMap.put(e.getDesc(), e.getCode()));
    }

    CommonStatus(Byte code, String desc) {
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
