github远程仓库地址:

已部署的地址：

# 需求分析

主要就是用户模块和文件上传模块

## 用户模块

- 登录

  已经登录无法再次重复登录

  没有登录就不让你上传文件

  登录用户名不存在-则信息回显 重新回到登录界面

  登录密码错误-则信息回显 重新回到登录界面

- 注册

  已经登录无法再次注册

  除了用户名不能重复其他没啥要求

- 注销

  点击注销按钮退回到登录界面

## 文件模块

- 展示所有文件
- 删除指定文件
- 查看文件大图

# 数据库表设计

一共两张表，user和upload_file

user用户表

![image-20211006164653749](大宝贝助手.assets/image-20211006164653749.png)

upload_file上传文件表----持有userId的外键

![image-20211006164618644](大宝贝助手.assets/image-20211006164618644.png)

# 用到的和学到的技术

## 前端

HTML CSS JS

BootStrap

## 后端

SpringBoot（<font color='red'>快速构建</font>）

Thymeleaf模板引擎（<font color='red'>前后端分离青春版</font>）

Mybatis-Plus（<font color='red'>自动生成简单SQL+分页插件+Wrapper条件查询多表连接查询</font>）

数据源：Druid

## 部署

Docker容器化部署（<font color='red'>这个之前一直拖着现在终于学了一下</font>）

# 前端页面

## 点击按钮上传

![image-20211006163927482](大宝贝助手.assets/image-20211006163927482.png)

## 鼠标移入变色动画（这个图片不好展示）

![image-20211006163939856](大宝贝助手.assets/image-20211006163939856.png)

## 导航栏

![image-20211006165034533](大宝贝助手.assets/image-20211006165034533.png)

# 用户模块-实体类

```java
public class User {
   @TableId(type = IdType.AUTO)
   private Integer id;
   private String username;
   private String password;
   private  String email;
}
```

# 用户模块-登录

用户根据注册的账号和密码进行登录，如果不登录，显示的是欢迎 游客；如果登录了，显示的是欢迎 用户名

## 用户没登录

![image-20211006161954752](大宝贝助手.assets/image-20211006161954752.png)

## 用户名不存在

![image-20211006163537038](大宝贝助手.assets/image-20211006163537038.png)

![image-20211006163554331](大宝贝助手.assets/image-20211006163554331.png)

## 用户名存在但是密码错误

![image-20211006163630225](大宝贝助手.assets/image-20211006163630225.png)

![image-20211006163637200](大宝贝助手.assets/image-20211006163637200.png)

## 用户正确登录了

![image-20211006162018094](大宝贝助手.assets/image-20211006162018094.png)

## 用户登录了还搁着登录，错误信息回显

![image-20211006162126745](大宝贝助手.assets/image-20211006162126745.png)

## 用户没登录就想着去上传，先给我去登录

![image-20211006163423935](大宝贝助手.assets/image-20211006163423935.png)

![image-20211006163446773](大宝贝助手.assets/image-20211006163446773.png)

# 用户模块-注册

用户根据用户名，密码和邮箱进行注册

## 用户已经登录则不让注册

![image-20211006162052504](大宝贝助手.assets/image-20211006162052504.png)

## 未登录才可以注册

![image-20211006162154502](大宝贝助手.assets/image-20211006162154502.png)

## 注册的用户名已经存在了（唯一）

![image-20211006163739965](大宝贝助手.assets/image-20211006163739965.png)

![image-20211006163750421](大宝贝助手.assets/image-20211006163750421.png)

# 用户模块-注销

## 注销后退回到登录界面

![image-20211006162319138](大宝贝助手.assets/image-20211006162319138.png)

![image-20211006162334782](大宝贝助手.assets/image-20211006162334782.png)

# 文件模块-实体类

```java
@TableName("upload_file")
public class UploadFile {
@TableId(type=IdType.AUTO)
     private  Integer id;
     private  String name;//对应本地磁盘路径下的文件名
     private Date date;
     private  Integer userId;
}
```

# 文件模块-展示某个用户所有的上传的文件

## 每次点击`我的大宝贝`就查询属于该用户的所有文件信息（包括名称，编号，创建日期）并渲染

![image-20211006162524515](大宝贝助手.assets/image-20211006162524515.png)

# 文件模块-分页条

## 前端BootStrap+Thymeleaf模板引擎根据后端返回总记录数渲染数据

![image-20211006162706071](大宝贝助手.assets/image-20211006162706071.png)

![image-20211006162606988](大宝贝助手.assets/image-20211006162606988.png)

![image-20211006162715231](大宝贝助手.assets/image-20211006162715231.png)

# 文件模块-点我查看大图

## 用户在页面上点击查看大图，展示详细大图

![image-20211006162759867](大宝贝助手.assets/image-20211006162759867.png)

![image-20211006162806971](大宝贝助手.assets/image-20211006162806971.png)

# 文件模块-删除图片

## 用户点击某个文件删除按钮，删除之后回到删除之前所在的页面

![image-20211006162841227](大宝贝助手.assets/image-20211006162841227.png)

![image-20211006162851389](大宝贝助手.assets/image-20211006162851389.png)
