package com.cloud.frame.framecommon.util;

import java.util.UUID;

/**
 * IdUtil
 * @author liulijun
 */
public class IdUtil {

    private static final SnowFlake snowFlake = new SnowFlake(2, 3);

    /**
     * 生成订单id
     * @param orderPrefix
     * @return
     */
    public static String orderId(String orderPrefix){
        return new StringBuilder(orderPrefix)
                .append(snowFlake.nextId())
                .toString();
    }

    /**
     * 生成主键id
     * @return
     */
    public static Long primaryKey(){
        return snowFlake.nextId();
    }

    /**
     * 生成uuid
     * @return
     */
    public static String randomId(){
        return UUID.randomUUID().toString();
    }
}

