package com.hyy.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * @author HuangSir
 * @create 2021-09-25 9:56
 */
@TableName("upload_file")
public class UploadFile {
   @TableId(type=IdType.AUTO)
private  Integer id;
private  String name;
private Date date;
private  Integer userId;

   public UploadFile(Integer id, String name, Date date, Integer userId) {
      this.id = id;
      this.name = name;
      this.date = date;
      this.userId = userId;
   }

   public UploadFile() {
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
   }

   @Override
   public String toString() {
      return "UploadFile{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", date=" + date +
              ", userId=" + userId +
              '}';
   }
}
