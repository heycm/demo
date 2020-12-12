package online.heycm.auth.config.mp;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MP分页配置
 * @Author heycm@qq.com
 * @Date 2020-10-31 19:44
 */
@Configuration
public class MPConfig {

    /**
     * 分页配置
     * 配置bean,将逻辑分页变为物理分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
