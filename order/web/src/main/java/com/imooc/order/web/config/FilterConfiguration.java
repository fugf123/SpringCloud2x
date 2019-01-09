package com.imooc.order.web.config;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * druid 监控
 */
@Configuration
public class FilterConfiguration {

    //druid SQL监控使用说明：https://blog.csdn.net/saytime/article/details/78963121

    @Bean
    public FilterRegistrationBean druidStatFilterBean() {
        FilterRegistrationBean druidStatFilterBean = new FilterRegistrationBean(new WebStatFilter());
        List<String> urlPattern = new ArrayList<>();
        urlPattern.add("/*");
        druidStatFilterBean.setUrlPatterns(urlPattern);
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        druidStatFilterBean.setInitParameters(initParams);
        return druidStatFilterBean;
    }
}
