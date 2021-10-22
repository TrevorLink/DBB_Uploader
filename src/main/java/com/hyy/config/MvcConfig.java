package com.hyy.config;

import com.hyy.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
      registry.addViewController("/uploadFile/goUpload").setViewName("upload");
//      registry.addViewController("/goRegister").setViewName("register");
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      //拦截登录以及上传文件有关的请求
      registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**","/uploadFile/**")
              .excludePathPatterns("/","/user/goLogin","/user/login","/user/goRegister","/css/**","/images/**");
   }
}
