package com.cloud.frame.framecommon.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {

    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    //全称:AddHeaderMap,增加的请求头
    private Map<String, String> ahm = new ConcurrentHashMap<>();

    //全称:removeHeaderMap,删除的请求头
    private Set<String> rhs = new CopyOnWriteArraySet<>();

    public void addRhs(String name) {
        rhs.add(name);
    }

    public void removeRhs(String name) {
        rhs.remove(name);
    }

    public void addAhm(String name, String value) {
        ahm.put(name, value);
    }

    public void removeAhm(String name) {
        ahm.remove(name);
    }

    /**
     * 得到请求头
     * @param name
     * @return
     */
    @Override
    public String getHeader(String name) {
        if(isRemoveHeader(name)){
            return null;
        }
        String headerValue = super.getHeader(name);
        if (ahm.containsKey(name)) {
            headerValue = ahm.get(name);
        }
        return headerValue;
    }

    /**
     * 得到所有的请求头名称
     * @return
     */
    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> names = new HashSet<>(Collections.list(super.getHeaderNames()));
        for (String name : ahm.keySet()) {
            if(!rhs.contains(name)){
                names.add(name);
            }
        }
        return Collections.enumeration(names);
    }

    /**
     * 根据名称，得到请求头数组
     * @param name
     * @return
     */
    @Override
    public Enumeration<String> getHeaders(String name) {
        if(isRemoveHeader(name)){
            return null;
        }
        List<String> values = Collections.list(super.getHeaders(name));
        if (ahm.containsKey(name)) {
            values = Arrays.asList(ahm.get(name));
        }
        return Collections.enumeration(values);
    }

    /**
     * 请求头是否已经被移除
     * @param name
     * @return
     */
    private Boolean isRemoveHeader(String name){
        return rhs.contains(name);
    }

}
