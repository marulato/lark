package org.avalon.lark.common.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.avalon.lark.common.consts.SystemConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyCache implements ICache {

    private static LoadingCache<String, String> propertyCache;
    private static final Logger log = LoggerFactory.getLogger(PropertyCache.class);
    private static Properties properties;

    public static void init() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(SystemConsts.getClassPath() + "lark.properties")));
        } catch (Exception e) {
            log.error("Error loading properties", e);
        }
        propertyCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                String v = properties.getProperty(s);
                if (v == null) {
                    v = "";
                }
                return v;
            }
        });
    }

    @Override
    public Object get(Object key) throws Exception {
        return propertyCache.get((String) key);
    }

    @Override
    public void set(Object key, Object value) throws Exception {
        throw new UnsupportedOperationException();
    }
}
