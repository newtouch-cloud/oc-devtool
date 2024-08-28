package com.geeke;

import com.newtouch.cloud.common.dependency.remote.config.RemoteConfig;
import com.newtouch.cloud.common.dependency.swagger.Swagger2Config;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: lys
 * @description: SpringBoot启动类
 * @date: 2017/10/24 11:55
 */
@Import({Swagger2Config.class, RemoteConfig.class})
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
// @MapperScan({"com.geeke.sys.dao", "com.geeke.common.dao"})
public class MyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(MyApplication.class);
    }
}
