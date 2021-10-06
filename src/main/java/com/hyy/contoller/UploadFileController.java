package com.hyy.contoller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyy.bean.UploadFile;
import com.hyy.bean.User;
import com.hyy.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author HuangSir
 * @create 2021-09-24 14:48
 */
@Controller
@RequestMapping("uploadFile")
public class UploadFileController {
   private UploadFileService uploadFileService;
   private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);
   @Value("${photo.file.dir}")
   private String realPath;

   @Autowired
   public void setUploadFileService(UploadFileService uploadFileService) {
      this.uploadFileService = uploadFileService;
   }

   /**
    * 如果没登录就不让你上传
    *
    * @param session
    * @param model
    * @return
    */
   @RequestMapping("goUpload")
   public String goUpload(HttpSession session, Model model) {
      User user = (User) session.getAttribute("user");
      if (user == null) {
         model.addAttribute("errorMsg", "您还未登录，请先登录后再上传文件！");
         logger.debug("您还未登录，请先登录后再上传文件！");
         return "login";
      }
      return "upload";
   }

   /**
    * 上传文件
    *
    * @param img
    * @return
    */
   @RequestMapping("upload")
   public String upload(MultipartFile img, HttpSession session) throws IOException {
      User user = (User) session.getAttribute("user");
      Integer userId = user.getId();
      logger.debug("收到的用户id为{}", userId);
      logger.debug("收到的图片信息为{}", img);
      String originalFilename = img.getOriginalFilename();
      img.transferTo(new File(realPath, originalFilename));
      UploadFile uploadFile = new UploadFile(null, originalFilename, new Date(new java.util.Date().getTime()), userId);
      logger.debug(uploadFile.toString());
      uploadFileService.save(uploadFile);
      return "redirect:/uploadFile/toMyUpload";
   }

   /**
    * 上传文件后/直接点击要先进行数据的查询（分页），查完之后再渲染
    *
    * @return
    */
   @RequestMapping("toMyUpload")
   public String toMyUpload(Model model, HttpSession session, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
      logger.debug("当前用户所在页码为{}", pageNo);
      //分页信息设置
      Page<UploadFile> page = new Page<>(pageNo, 4);
      User user = (User) session.getAttribute("user");
      Integer userId = user.getId();
      logger.debug("用户的userId为{}", userId);
      //设置条件查询的wrapper
      QueryWrapper<UploadFile> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("userId", userId);
      Page<UploadFile> uploadFilePage = uploadFileService.page(page, queryWrapper);
      logger.debug("总记录数为{}",uploadFilePage.getTotal());
      logger.debug("pages={}",uploadFilePage.getPages());
//      List<UploadFile> list = uploadFileService.list(queryWrapper);
      logger.debug("查到用户id为{}的用户上传到的图片的此次分页数据为{}", userId, uploadFilePage.getRecords());
      model.addAttribute("uploadFilePage", uploadFilePage);
      return "myupload";
   }

   /**
    * 根据前端传进来的id删除对应的图片，之后返回渲染数据的Controller再查一次
    *
    * @param id
    * @return
    */
   @RequestMapping("delete")
   public String delete(Integer id) {
      logger.debug("即将被删除的图片的id为{}", id);
      uploadFileService.removeById(id);
      return "redirect:/uploadFile/toMyUpload";
   }
}
