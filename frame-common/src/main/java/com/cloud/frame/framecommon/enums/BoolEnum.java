package com.cloud.frame.framecommon.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum BoolEnum {
    FALSE_ENUM((byte)0,false),
    TRUE_ENUM((byte)1,true);

    private Byte code;

    private Boolean bool;

    public static Map<Byte, Boolean> codeBoolMap = new HashMap<>();

    public static Map<Boolean, Byte> boolCodeMap = new HashMap<>();

    static {
        Arrays.stream(BoolEnum.values()).forEach(e -> codeBoolMap.put(e.getCode(), e.getBool()));
        Arrays.stream(BoolEnum.values()).forEach(e -> boolCodeMap.put(e.getBool(), e.getCode()));
    }

    BoolEnum(Byte code, Boolean bool) {
        this.code = code;
        this.bool = bool;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
}
