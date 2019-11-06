package com.cloud.frame.resourceserver.cache.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.ParserConfig;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import com.cloud.ftl.ftlbasic.utils.QueryKeyUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.frame.resourceserver.cache.IHolidayCache;
import com.cloud.frame.resourceclient.entity.Holiday;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * IHolidayCache cache实现类
 * @author lijun
 */
@Slf4j
public class HolidayCacheImpl extends BaseServiceImpl<Holiday> implements IHolidayCache {

    private final static Long DEFAULT_EXPIRE_TIMES = 30L;
    private final static TimeUnit DEFAULT_EXPIRE_TIMEUNIT = TimeUnit.MINUTES;
    private final static String CLS_NAME = Holiday.class.getSimpleName();
    private final static String QUERY_KEY = CLS_NAME.concat(":Query:");

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Holiday cacheSelectById(Serializable id, String... nullErrMsg) {
        String entityKey = CLS_NAME.concat(":").concat(String.valueOf(id));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Holiday cacheVal = (Holiday)redisTemplate.opsForValue().get(entityKey);
        if(Objects.nonNull(cacheVal)){
            return cacheVal;
        }
        Holiday dbVal = super.selectById(id, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(entityKey,dbVal);
            redisTemplate.expire(entityKey,DEFAULT_EXPIRE_TIMES, DEFAULT_EXPIRE_TIMEUNIT);
        }
        return dbVal;
    }

    @Override
    public Holiday cacheSelectOne(Holiday query, String... nullErrMsg) {
        query.setPage(1);
        query.setPageSize(1);
        String queryKey = QUERY_KEY.concat("SelectOne:")
                .concat(QueryKeyUtil.getQueryKey(query, Boolean.TRUE));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Holiday cacheVal = (Holiday)redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cacheVal)){
            return cacheVal;
        }
        Holiday dbVal = super.selectOne(query, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(queryKey,dbVal);
            redisTemplate.expire(queryKey,DEFAULT_EXPIRE_TIMES, DEFAULT_EXPIRE_TIMEUNIT);
        }
        return dbVal;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Holiday> cacheSelectList(Holiday query, String... emptyErrMsg) {
        String queryKey = QUERY_KEY.concat("SelectList:")
                .concat(QueryKeyUtil.getQueryKey(query, Boolean.FALSE));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        List<String> cacheKeys = (List<String>)redisTemplate.opsForValue().get(queryKey);
        if(!CollectionUtils.isEmpty(cacheKeys)){
            List<Object> cacheValues = redisTemplate.opsForValue().multiGet(cacheKeys);
            if(Objects.nonNull(cacheValues) && cacheKeys.size() == cacheValues.size()){
                return JSONArray.parseArray(JSON.toJSONString(cacheValues), Holiday.class);
            }
        }
        List<Holiday> dbValues = super.selectList(query, emptyErrMsg);
        List<String> dbKeys = Lists.newArrayList();
        dbValues.forEach(e -> {
            String entityKey = CLS_NAME.concat(":").concat(String.valueOf(e.getHId()));
            dbKeys.add(entityKey);
            redisTemplate.opsForValue().set(entityKey,e);
            redisTemplate.expire(entityKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
        });
        redisTemplate.opsForValue().set(queryKey,dbKeys);
        redisTemplate.expire(queryKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
        return dbValues;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Holiday> cacheSelectList(Holiday query, List<String> fieldList, String... emptyErrMsg) {
        if(CollectionUtils.isEmpty(fieldList)){
            throw new BusiException("请指定查询的域");
        }
        String queryKey = QUERY_KEY.concat("FieldSelectList:")
                .concat(QueryKeyUtil.getQueryKey(query, Boolean.FALSE,fieldList.toArray(new String[fieldList.size()])));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Object cacheValues = redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cacheValues)){
            return JSONArray.parseArray(JSON.toJSONString(cacheValues),Holiday.class);
        }
        List<Holiday> dbValues = super.selectList(query, fieldList, emptyErrMsg);
        if(!CollectionUtils.isEmpty(dbValues)){
            redisTemplate.opsForValue().set(queryKey,dbValues);
            redisTemplate.expire(queryKey,DEFAULT_EXPIRE_TIMES, DEFAULT_EXPIRE_TIMEUNIT);
        }
        return dbValues;
    }

    @Override
    public List<Holiday> cacheSelectBatchIds(Collection<? extends Serializable> list, String... emptyErrMsg) {
        List<String> queryKeys = list.stream().map(e -> CLS_NAME.concat(":").concat(String.valueOf(e))).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(queryKeys)){
            List<Object> cacheValues = redisTemplate.opsForValue().multiGet(queryKeys);
            if(Objects.nonNull(cacheValues)){
                List<Holiday> cacheEntitys = JSONArray.parseArray(JSON.toJSONString(cacheValues), Holiday.class);
                if(queryKeys.size() == cacheEntitys.size()){
                    return cacheEntitys;
                } else {
                    List<String> cacheKeys = cacheEntitys.stream()
                        .map(e -> CLS_NAME.concat(":").concat(String.valueOf(e.getHId())))
                        .collect(Collectors.toList());
                    queryKeys.removeAll(cacheKeys);
                    List<Holiday> dbValues = super.selectBatchIds(queryKeys, emptyErrMsg);
                    if(!CollectionUtils.isEmpty(dbValues)){
                        cacheEntitys.addAll(dbValues);
                        dbValues.forEach(e -> {
                            String entityKey = CLS_NAME.concat(":").concat(String.valueOf(e.getHId()));
                            redisTemplate.opsForValue().set(entityKey,e);
                            redisTemplate.expire(entityKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
                        });
                    }
                    return cacheEntitys;
                }
            } else {
                List<Holiday> dbValues = super.selectBatchIds(list, emptyErrMsg);
                dbValues.forEach(e -> {
                    String entityKey = CLS_NAME.concat(":").concat(String.valueOf(e.getHId()));
                    redisTemplate.opsForValue().set(entityKey,e);
                    redisTemplate.expire(entityKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
                });
                return dbValues;
            }
        }
        return Lists.newArrayList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public PageBean<Holiday> cacheSelectPage(Holiday query) {
        String queryKey = QueryKeyUtil.getQueryKey(query, Boolean.TRUE);
        String pageBeanKey = QUERY_KEY.concat("SelectPage:PageBean:").concat(queryKey);
        String pageIdsKey = QUERY_KEY.concat("SelectPage:Ids:").concat(queryKey);
        PageBean<Holiday> cachePageBean = (PageBean<Holiday>)redisTemplate.opsForValue().get(pageBeanKey);
        List<String> cahcePageKeys = (List<String>)redisTemplate.opsForValue().get(pageIdsKey);
        if(Objects.nonNull(cachePageBean) && Objects.nonNull(cahcePageKeys)){
        List<Object> cacheValues = redisTemplate.opsForValue().multiGet(cahcePageKeys);
            if(Objects.isNull(cacheValues)){
                cacheValues = Lists.newArrayList();
            }
            if(cahcePageKeys.size() == cacheValues.size()){
            List<Holiday> list = JSONArray.parseArray(JSON.toJSONString(cacheValues), Holiday.class);
                cachePageBean.setList(list);
                return cachePageBean;
            }
        }
        PageBean<Holiday> dbPageBean = super.selectPage(query);
        List<String> dbPageKeys = Lists.newArrayList();
        dbPageBean.getList().forEach(e -> {
            String entityKey = CLS_NAME.concat(":").concat(String.valueOf(e.getHId()));
            dbPageKeys.add(entityKey);
            redisTemplate.opsForValue().set(entityKey,e);
            redisTemplate.expire(entityKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
        });
        redisTemplate.opsForValue().set(pageBeanKey,new PageBean<>(dbPageBean.getTotalPage(),dbPageBean.getTotal(),null));
        redisTemplate.opsForValue().set(pageIdsKey,dbPageKeys);
        redisTemplate.expire(pageBeanKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
        redisTemplate.expire(pageIdsKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
        return dbPageBean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PageBean<Holiday> cacheSelectPage(Holiday query, List<String> fieldList) {
        String queryKey = QUERY_KEY.concat("FieldSelectPage:")
                .concat(QueryKeyUtil.getQueryKey(query, Boolean.TRUE,fieldList.toArray(new String[fieldList.size()])));
        PageBean<Holiday> cachePageBean = (PageBean<Holiday>)redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cachePageBean)){
            return cachePageBean;
        }
        PageBean<Holiday> dbPageBean = super.selectPage(query, fieldList);
        redisTemplate.opsForValue().set(queryKey,dbPageBean);
        redisTemplate.expire(queryKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
        return dbPageBean;
    }

    @Override
    public Long cacheSelectCount(Holiday query) {
        String queryKey = QUERY_KEY.concat("SelectCount:")
                .concat(QueryKeyUtil.getQueryKey(query, Boolean.FALSE));
        Long cacheCount = (Long)redisTemplate.opsForValue().get(queryKey);
        if(Objects.nonNull(cacheCount)){
            log.info("Cache Class = {}, Entity = {} Count",Holiday.class.getName(),query);
            return cacheCount;
        }
        Long dbCount = super.selectCount(query);
        redisTemplate.opsForValue().set(queryKey,dbCount);
        redisTemplate.expire(queryKey,DEFAULT_EXPIRE_TIMES,DEFAULT_EXPIRE_TIMEUNIT);
        return dbCount;
    }

    @Override
    public int update(Holiday entity, Update... args) {
        int updateCount = super.update(entity, args);
        clearAllCacheData();
        return updateCount;
    }

    @Override
    public int updateByMap(Holiday oEntity, Map<String, Object> updateMap) {
        int updateCount = super.updateByMap(oEntity, updateMap);
        clearAllCacheData();
        return updateCount;
    }

    @Override
    public void updateBatch(List<Holiday> list, Update... args) {
        super.updateBatch(list, args);
        clearAllCacheData();
    }

    @Override
    public int add(Holiday entity) {
        int addCount = super.add(entity);
        clearAllCacheData();
        return addCount;
    }

    @Override
    public void addBatch(List<Holiday> list) {
        super.addBatch(list);
        clearAllCacheData();
    }

    @Override
    public void addBatch(List<Holiday> list, int batchSize) {
        super.addBatch(list, batchSize);
        clearAllCacheData();
    }

    @Override
    public void delete(Holiday entity) {
        super.delete(entity);
        clearAllCacheData();
    }

    @Override
    public int deleteById(Serializable id) {
        int deleteCount = super.deleteById(id);
        clearAllCacheData();
        return deleteCount;
    }

    @Override
    public void deleteBatchIds(Collection<? extends Serializable> list) {
        super.deleteBatchIds(list);
        clearAllCacheData();
    }

    @Override
    public void save(Holiday holiday, Update... args) {
        super.save(holiday, args);
        clearAllCacheData();
    }

    @Override
    public void saveBatch(List<Holiday> list, Update... args) {
        super.saveBatch(list, args);
        clearAllCacheData();
    }

    private void clearAllCacheData(){
        try {
            Set<String> keys = redisTemplate.keys(Holiday.class.getSimpleName() + "*");
            if(!CollectionUtils.isEmpty(keys)){
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            log.error("清除缓存失败，请手动处理",e);
        }
    }

}