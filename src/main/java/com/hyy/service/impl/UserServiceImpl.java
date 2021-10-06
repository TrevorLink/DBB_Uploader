package com.hyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyy.bean.User;
import com.hyy.mapper.UserMapper;
import com.hyy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @create 2021-09-24 19:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
