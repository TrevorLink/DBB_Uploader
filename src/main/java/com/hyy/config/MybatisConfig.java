package com.hyy.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuangSir
 * @create 2021-09-18 21:48
 */
@Configuration
public class MybatisConfig {
   @Bean
   public MybatisPlusInterceptor mybatisPlusInterceptor() {
      MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
      //创建分页拦截器
      PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
      //设置拦截器的各项参数
      // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
      paginationInterceptor.setOverflow(true);
      // 设置最大单页限制数量，默认 500 条，-1 不受限制
      paginationInterceptor.setMaxLimit(500L);
      //把分页拦截器加入到大的拦截器中
      mybatisPlusInterceptor.addInnerInterceptor(paginationInterceptor);
      return mybatisPlusInterceptor;
   }
}