package com.imooc.order.web.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
@ConfigurationProperties(prefix = "spring.datasource.druid")
@PropertySource(value="classpath:druid.properties")
public class DruidDataSource {

    /**
     * @see

     * @return DruidDataSource
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

}
