package com.zhangheng.history.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhangheng.history.util.FileUtil;
import com.zhangheng.history.util.ResultEnum;
import com.zhangheng.history.util.ResultInfo;
import com.zhangheng.history.util.ResultUtil;
import com.zhangheng.history.util.StringUtil;

@RestController
@RequestMapping("/upload/")
public class UploadController {
    //处理文件上传
    @RequestMapping(value="/file", method = RequestMethod.POST)
    public ResultInfo<Object>  uploadImg(@RequestParam("file") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) {
//    	response.setHeader("Access-Control-Allow-Origin", "*"); 
        //String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String filePath = StringUtil.isLinux()?ResultEnum.LINUXUPLOADPATH.getMsg():ResultEnum.WINDOWUPLOADPATH.getMsg();
//        String filePath = ResultEnum.LINUXUPLOADPATH.getMsg();
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        Map<String, Object> data =new HashMap<>();
        data.put("src", filePath+fileName);
        return ResultUtil.success(ResultEnum.SUCCESS,data);
    }
}
