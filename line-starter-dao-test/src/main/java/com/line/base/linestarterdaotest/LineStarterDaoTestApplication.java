package com.line.base.linestarterdaotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.line"})
// tkMapper dao 继承 Mapper
// mapperSacn 切换成 tk.mybatis.spring.annotation.MapperScan
@MapperScan(basePackages = "com.line.base.linestarterdaotest")
public class LineStarterDaoTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(LineStarterDaoTestApplication.class, args);
    }
}
