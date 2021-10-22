package com.hyy.interceptor;

import com.hyy.bean.User;
import com.hyy.contoller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 * @author HuangSir
 * @create 2021-10-22 14:26
 */
public class LoginInterceptor implements HandlerInterceptor {
   //日志
   private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      logger.debug(request.getRequestURI()+"请求被拦截");
      HttpSession session = request.getSession();
      User user = (User) session.getAttribute("user");
      if (user!=null){
         return  true;
      }
      request.setAttribute("errorMsg","您还未登录，请先登录！");
      logger.debug("您还未登录，请先登录！");
      request.getRequestDispatcher("/").forward(request,response);
      return false;
   }
}
