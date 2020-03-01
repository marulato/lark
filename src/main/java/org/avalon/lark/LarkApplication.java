package org.avalon.lark;

import org.avalon.lark.common.cache.ConfigCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LarkApplication {

    public static void main(String[] args) {
        SpringApplication.run(LarkApplication.class, args);
        ConfigCache.init();
    }

}
