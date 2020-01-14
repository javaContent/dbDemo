package com.example.two;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.example.two.dao")
//排除DataSource自动配置类,否则会默认自动配置,不会使用我们自定义的DataSource,并且启动报错
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoApplication.class, args);
    }

}
