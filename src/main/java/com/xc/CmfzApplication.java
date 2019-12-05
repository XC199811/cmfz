package com.xc;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by lenovo on 2019/11/26.
 */
@SpringBootApplication
@MapperScan("com.xc.dao")
public class CmfzApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class,args);
    }
}
