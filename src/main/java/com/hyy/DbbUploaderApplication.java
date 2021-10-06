package com.hyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hyy.mapper")
public class DbbUploaderApplication {

   public static void main(String[] args) {
      SpringApplication.run(DbbUploaderApplication.class, args);
   }

}
