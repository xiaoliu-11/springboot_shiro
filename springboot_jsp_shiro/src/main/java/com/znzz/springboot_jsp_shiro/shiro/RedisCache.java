package com.znzz.springboot_jsp_shiro.shiro;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

//自定义redis缓存的实现

public class RedisCache<k,v> implements Cache<k,v> {


    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public v get(k key) throws CacheException {
        System.out.println("get key: "+ key);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return (v) redisTemplate.opsForValue().get(key.toString());
    }

    @Override
    public v put(k key, v value) throws CacheException {
        System.out.println("put key:"+key);
        System.out.println("put value:"+value);

        // 将用户的信息放入redis
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(key.toString(),value);

        return null;
    }

    @Override
    public v remove(k key) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }
}
