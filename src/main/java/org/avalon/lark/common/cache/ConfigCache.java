package org.avalon.lark.common.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.avalon.lark.common.utility.LogUtils;
import org.avalon.lark.common.utility.SpringUtils;
import org.avalon.lark.systemadmin.dao.ConfigDao;
import org.avalon.lark.systemadmin.entity.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigCache implements ICache {

    private static LoadingCache<String, Config> configCache;
    private static final Logger log = LoggerFactory.getLogger(ConfigCache.class);

    public static void init() {
        ConfigDao configDao = SpringUtils.getBean(ConfigDao.class);
        configCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Config>() {
            @Override
            public Config load(String key) throws Exception {
                log.debug(key + " has been cached");
                return  configDao.getConfig(key);
            }
        });
        log.info(LogUtils.printLine(10, "*") +
                "Config cache initialized" + LogUtils.printLine(10, "*"));
    }

    @Override
    public Object get(Object key) throws Exception {
        return configCache.get((String) key);
    }

    @Override
    public void set(Object key, Object value) throws Exception {
        throw new UnsupportedOperationException();
    }
}
