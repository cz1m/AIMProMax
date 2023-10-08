package com.like4u.client.infrastructure.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/7 21:11
 */
public class BeanUtil {

    private static Map<String,Object> cache=new ConcurrentHashMap<>();

    public synchronized static void addBean(String key,Object value){
        cache.put(key,value);
    }
    public static  <T> T getBean(String key,Class<T> t){
        return (T) cache.get(key);
    }
}
