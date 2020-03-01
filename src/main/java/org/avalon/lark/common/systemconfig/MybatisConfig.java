package org.avalon.lark.common.systemconfig;

import org.avalon.lark.common.database.crud.SQLInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    @Bean
    public SQLInterceptor getSQLInterceptor() {
        return new SQLInterceptor();
    }
}
