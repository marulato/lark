package org.avalon.lark.common.utility;

import org.avalon.lark.common.cache.ConfigCache;
import org.avalon.lark.systemadmin.entity.Config;

public class ConfigUtils {

    private static ConfigCache configCache = new ConfigCache();

    public static String get(String key) {
        String value = null;
        try {
            value = ((Config) configCache.get(key)).getConfigValue();
        } catch (Exception e) {
            //
        }
        return value;
    }
}
