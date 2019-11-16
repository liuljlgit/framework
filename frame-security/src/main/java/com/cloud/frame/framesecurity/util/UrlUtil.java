package com.cloud.frame.framesecurity.util;

import org.springframework.util.AntPathMatcher;

import java.util.Set;

/**
 * url权限路径匹配工具类
 * @author Liulj
 * @version v 0.1 2019/11/16 10:44
 */
public class UrlUtil {

    public static Boolean matching(Set<String> patternSets, String serverPath){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return patternSets.stream().anyMatch(e -> antPathMatcher.match(e, serverPath));
    }

}
