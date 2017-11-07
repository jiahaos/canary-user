package com.canary.biz.acl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

/**
 * Created by hsk on 2016/1/13.
 */
@Slf4j
@SpringBootApplication
@ComponentScan({
        "com.canary",
        "com.jaf"
})
@EntityScan({
        "com.canary.biz.acl.enti"
})
@MapperScan({
        "com.canary.biz.acl.repo"
})
@Controller
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
    }

}

