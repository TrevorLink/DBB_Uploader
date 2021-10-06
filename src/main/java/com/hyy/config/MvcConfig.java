package com.hyy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author HuangSir
 * @create 2021-09-24 14:03
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("login");
//      registry.addViewController("/goRegister").setViewName("register");
   }
}
