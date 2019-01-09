package com.imooc.order.web.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = "classpath:druid.properties")
public class ServletConfiguration {

    //druid SQL监控使用说明：https://blog.csdn.net/saytime/article/details/78963121

	@Value("${spring.datasource.druid.stat-view-servlet.login-username}")
	private String userName;
	@Value("${spring.datasource.druid.stat-view-servlet.login-password}")
	private String password;
	@Value("${spring.datasource.druid.stat-view-servlet.allow}")
	private String allow;
	@Value("${spring.datasource.druid.stat-view-servlet.reset-enable}")
	private String resetEnable;
    @Value("${spring.datasource.druid.stat-view-servlet.url-pattern}")
    private String urlPattern;

    @Bean
    public ServletRegistrationBean druidStatViewServletBean() {
        // 后台的路径
        ServletRegistrationBean statViewServletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> params = new HashMap<>();
        // 账号密码，是否允许重置数据
        params.put("loginUsername", userName);
        params.put("loginPassword", password);
        params.put("allow", allow);
        params.put("resetEnable", resetEnable);
        statViewServletRegistrationBean.setInitParameters(params);
        statViewServletRegistrationBean.addUrlMappings(urlPattern);
        return statViewServletRegistrationBean;
    }
}
