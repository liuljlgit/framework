package com.cloud.frame.framecommon.util;

import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 公共工具类
 * @author lijun
 */
public class CommonUtil {

    public static void isNull(Object obj,String errMsg) {
        if(Objects.isNull(obj)) {
            throw new RuntimeException(errMsg);
        }
    }

    public static void nonNull(Object obj,String errMsg) {
        if(Objects.nonNull(obj)) {
            throw new RuntimeException(errMsg);
        }
    }

    public static void isEmpty(String str,String errMsg) {
        if(StringUtils.isEmpty(str)) {
            throw new RuntimeException(errMsg);
        }
    }

    public static void isNotEmpty(String str,String errMsg) {
        if(!StringUtils.isEmpty(str)) {
            throw new RuntimeException(errMsg);
        }
    }

}
