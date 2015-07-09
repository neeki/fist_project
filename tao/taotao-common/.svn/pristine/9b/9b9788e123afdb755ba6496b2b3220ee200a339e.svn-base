package com.taotao.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired(required = false)
    private JedisPool jedisPool;

    private static final Integer DEFAULT_DB_INDEX = 0;

    private <K> K execute(Function<Jedis, K> fun, Integer index) {
        Jedis jedis = null;
        Boolean selectDB = false;
        try {
            // 从连接池中获取连接
            jedis = jedisPool.getResource();
            if (index != null && index.intValue() != DEFAULT_DB_INDEX) {// 选中传入的数据库
                jedis.select(index);
                selectDB = true;
            }
            return fun.callback(jedis);
        } finally {
            if (selectDB) {// 恢复默认选中数据库
                jedis.select(DEFAULT_DB_INDEX);
            }
            // 将连接还回到连接池中
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 设置一个值
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return this.set(DEFAULT_DB_INDEX, key, value);
    }

    /**
     * 设置一个值
     * 
     * @param index 数据库名
     * @param key
     * @param value
     * @return
     */
    public String set(Integer index, final String key, final String value) {
        return this.execute(new Function<Jedis, String>() {
            @Override
            public String callback(Jedis jedis) {
                return jedis.set(key, value);
            }
        }, index);
    }

    /**
     * 设置一个值，并且指定生存时间
     * 
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
        return this.set(DEFAULT_DB_INDEX, key, value, seconds);
    }

    /**
     * 设置一个值，并且指定生存时间
     * 
     * @param index 数据库名
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public String set(Integer index, final String key, final String value, final Integer seconds) {
        return this.execute(new Function<Jedis, String>() {
            @Override
            public String callback(Jedis jedis) {
                String str = jedis.set(key, value);
                jedis.expire(key, seconds);
                return str;
            }
        }, index);
    }

    /**
     * 为key设置生存时间
     * 
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final Integer seconds) {
        return this.expire(DEFAULT_DB_INDEX, key, seconds);
    }

    /**
     * 为key设置生存时间
     * 
     * @param index 数据库名
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(Integer index, final String key, final Integer seconds) {
        return this.execute(new Function<Jedis, Long>() {
            @Override
            public Long callback(Jedis jedis) {
                return jedis.expire(key, seconds);
            }

        }, index);
    }

    /**
     * 获取数据
     * 
     * @param key
     * @return
     */
    public String get(final String key) {
        return this.get(DEFAULT_DB_INDEX, key);
    }

    /**
     * 获取数据
     * 
     * @param index 数据库名
     * @param key
     * @return
     */
    public String get(Integer index, final String key) {
        return this.execute(new Function<Jedis, String>() {
            @Override
            public String callback(Jedis e) {
                return e.get(key);
            }
        }, index);
    }

    /**
     * 删除key
     * 
     * @param key
     * @return
     */
    public Long del(final String key) {
        return this.del(DEFAULT_DB_INDEX, key);
    }

    /**
     * 删除key
     * 
     * @param index 数据库名
     * @param key
     * @return
     */
    public Long del(Integer index, final String key) {
        return this.execute(new Function<Jedis, Long>() {
            @Override
            public Long callback(Jedis e) {
                return e.del(key);
            }
        }, index);
    }

}
