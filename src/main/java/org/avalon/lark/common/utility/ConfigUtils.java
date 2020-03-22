package org.avalon.lark.common.utility;

import com.google.common.cache.CacheLoader;
import org.avalon.lark.common.cache.ConfigCache;
import org.avalon.lark.common.cache.PropertyCache;
import org.avalon.lark.systemadmin.entity.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtils {

    private static ConfigCache configCache = new ConfigCache();
    private static PropertyCache propertyCache = new PropertyCache();
    private static final Logger log = LoggerFactory.getLogger("Configurations");

    public static String getString(String key)  {
        String value = null;
        try {
            Object obj = propertyCache.get(key);
            if (obj != null) {
                value = (String) obj;
            }
            if (StringUtils.isEmpty(value)) {
                value = ((Config) configCache.get(key)).getConfigValue();
            }
        } catch (Exception e) {
            if (e instanceof CacheLoader.InvalidCacheLoadException) {
                value = null;
            } else {
                log.error("Unable to retrieve data from cache", e);
            }
        }
        return value;
    }
}
