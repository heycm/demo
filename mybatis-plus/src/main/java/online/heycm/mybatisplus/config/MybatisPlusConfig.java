package online.heycm.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-08-01 23:13
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页配置
     * 配置bean,将逻辑分页变为物理分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
