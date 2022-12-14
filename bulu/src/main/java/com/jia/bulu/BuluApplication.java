package com.jia.bulu;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@Slf4j
@EnableTransactionManagement
@EnableCaching
public class BuluApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuluApplication.class,args);
        log.info("项目启动成功");
    }
}
