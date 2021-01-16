package com.znzz.springboot_jsp_shiro.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 自定义的shiro缓存管理器
 *
 */
public class RedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        System.out.println("");
        return new  RedisCache<K,V>();
    }
}
