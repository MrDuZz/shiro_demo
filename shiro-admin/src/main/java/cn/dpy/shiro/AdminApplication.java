package cn.dpy.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/1 15:58
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.dpy.shiro.*")
@EnableAutoConfiguration
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
