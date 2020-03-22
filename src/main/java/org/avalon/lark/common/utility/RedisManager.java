package org.avalon.lark.common.utility;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public final class RedisManager {

    private static StringRedisTemplate stringTemplate = SpringUtils.getBean(StringRedisTemplate.class);


    public static void setString(String key, String value) {
        if (StringUtils.isNotBlank(key)) {
            stringTemplate.opsForValue().set(key, value);
        }
    }

    public static void setString(String key, String value, long time, TimeUnit timeUnit) {
        if (StringUtils.isNotBlank(key)) {
            stringTemplate.opsForValue().set(key, value, time, timeUnit);
        }
    }

    public static void leftPush(String key, String... value) {
        if (StringUtils.isNotBlank(key) && value != null && value.length > 0) {
            stringTemplate.opsForList().leftPushAll(key, value);
        }
    }

    public static void leftPush(String key, Collection<String> values) {
        if (StringUtils.isNotBlank(key) && values != null && !values.isEmpty()) {
            stringTemplate.opsForList().leftPushAll(key, values);
        }
    }

    public static void rightPush(String key, String... value) {
        if (StringUtils.isNotBlank(key) && value != null && value.length > 0) {
            stringTemplate.opsForList().rightPushAll(key, value);
        }
    }

    public static void rightPush(String key, Collection<String> values) {
        if (StringUtils.isNotBlank(key) && values != null && !values.isEmpty()) {
            stringTemplate.opsForList().rightPushAll(key, values);
        }
    }

    public static String getString(String key) {
        return stringTemplate.opsForValue().get(key);
    }

    public static String getString(String key, long index) {
        return stringTemplate.opsForList().index(key, index);
    }

    public static void remove(String key) {
        stringTemplate.delete(key);
    }
}
