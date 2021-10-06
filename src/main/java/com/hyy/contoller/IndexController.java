package com.hyy.contoller;

import com.hyy.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author HuangSir
 * @create 2021-09-27 21:18
 */
@Controller
public class IndexController {
   private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

   @RequestMapping("/")
   public  String goLogin(HttpSession session, RedirectAttributes attributes){
      User sessionUser = (User) session.getAttribute("user");
      if (sessionUser!=null){
         attributes.addFlashAttribute("errorMsg","您已经登录请勿重复登录！");
         logger.debug("您已经登录请勿重复登录！");
         return "redirect:/uploadFile/goUpload";
      }
      return "login";
   }
}
