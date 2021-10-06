package com.hyy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyy.bean.UploadFile;
import com.hyy.mapper.UploadFileMapper;
import com.hyy.service.UploadFileService;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @create 2021-09-25 10:07
 */
@Service
public class UploadFileServiceImpl extends ServiceImpl<UploadFileMapper, UploadFile> implements UploadFileService {
}
