package online.heycm.auth.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-10-31 20:25
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>();
        bean.setFilter(new CorsFilter());
        bean.setOrder(0);
        bean.addUrlPatterns("/*");
        bean.setName("CorsFilter");
        return bean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<AuthFilter>();
        bean.setFilter(new AuthFilter());
        bean.setOrder(1);
        bean.addUrlPatterns("/*");
        bean.setName("AuthFilter");
        return bean;
    }
}
