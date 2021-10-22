package com.hyy.contoller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyy.bean.User;
import com.hyy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author HuangSir
 * @create 2021-09-24 15:15
 */
@Controller
@RequestMapping("user")
public class UserController {
   private UserService userService;
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   @Autowired
   public void setUserService(UserService userService) {
      this.userService = userService;
   }

@RequestMapping("goLogin")
public  String goLogin(HttpSession session,RedirectAttributes attributes){
   User sessionUser = (User) session.getAttribute("user");
   if (sessionUser!=null){
      attributes.addFlashAttribute("errorMsg","您已经登录请勿重复登录！");
      logger.debug("您已经登录请勿重复登录！");
      return "redirect:/uploadFile/goUpload";
   }
   return "login";
}
@RequestMapping("goRegister")
public  String goRegister(HttpSession session,RedirectAttributes attributes){
   User sessionUser = (User) session.getAttribute("user");
   if (sessionUser!=null){
      attributes.addFlashAttribute("errorMsg","您已经登录请先注销后再注册！");
      logger.debug("您已经登录请先注销后再注册！");
      return "redirect:/uploadFile/goUpload";
   }
   return "register";
}
   @RequestMapping("login")
   public String login(User user, HttpSession session,Model model) {
         logger.debug(user.toString());
         QueryWrapper<User> wrapper = new QueryWrapper<>();
         wrapper.eq("username", user.getUsername());
         User userByName = userService.getOne(wrapper);
         if (userByName == null) {
            model.addAttribute("errorMsg", "用户不存在！");
            logger.debug("用户不存在");
            return "login";
         }
         logger.debug(userByName.toString());
         if (!userByName.getPassword().equals(user.getPassword())) {
            model.addAttribute("errorMsg", "用户名或密码错误！");
            logger.debug("用户名或密码错误");
            return "login";
         }
         session.setAttribute("user", userByName);
         return "redirect:/uploadFile/goUpload";
   }
   @RequestMapping("register")
   public String register(User user,HttpSession session,Model model){
      QueryWrapper<User> wrapper = new QueryWrapper<>();
      wrapper.eq("username", user.getUsername());
      User userByName = userService.getOne(wrapper);
      if (userByName!=null){
         model.addAttribute("errorMsg","该用户名已存在，请换一个试试");
         logger.debug("该用户名已存在，请换一个试试");
         return "register";
      }
      userService.save(user);
      return "redirect:/user/goLogin";
   }
   @RequestMapping("goMyUpload")
   public String goMyUpload(HttpSession session, Model model, RedirectAttributes attributes){
      User user = (User) session.getAttribute("user");
//      if (user==null){
//         model.addAttribute("errorMsg","您还未登录，请先登录后再查看我的上传！");
//         logger.debug("您还未登录，请先登录后再查看我的上传！");
//         return "login";
//      }
      logger.debug("想要去查看我的上传的用户的id为{}",user.getId());
      attributes.addAttribute("userId",user.getId());
      return "redirect:/uploadFile/toMyUpload";
   }
   @RequestMapping("logOut")
   public  String logOut(HttpSession  session){
      session.invalidate();
      return "redirect:/";
   }

}
