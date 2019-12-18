package com.chaoxing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Create by tachai on 2019-12-09 15:26
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }

//    @Bean
//    public SpringContextHolder springContextHolder() {
//        return new SpringContextHolder();
//    }
}
