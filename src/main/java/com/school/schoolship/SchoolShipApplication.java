package com.school.schoolship;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zouzhiyin
 */
@SpringBootApplication
@MapperScan(basePackages = "com.school.schoolship.model.dao")
@EnableSwagger2
@EnableCaching
public class SchoolShipApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolShipApplication.class, args);
    }

}
