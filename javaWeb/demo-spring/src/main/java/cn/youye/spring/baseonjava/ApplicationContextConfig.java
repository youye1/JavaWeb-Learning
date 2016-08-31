package cn.youye.spring.baseonjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * java配置spring bean
 * Created by pc on 2016/8/16.
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    public String message(){

        return "hello message";
    }
}
