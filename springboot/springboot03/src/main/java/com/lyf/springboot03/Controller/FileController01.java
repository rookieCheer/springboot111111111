package com.lyf.springboot03.Controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/01")
public class FileController01 {
    //跳转到上传文件的页面
    @RequestMapping(value = "/goUploadHtml", method = RequestMethod.GET)
    public String goUploadHtml() {
        //跳转到 templates 目录下的 upload.html
        return "upload01";
    }
    /**
     *   /处理单个文件上传 方式一，使用MultipartFile作为方法参数接收传入的文件
     * @RequestParam("file")中的file对应input标签中的name属性值
     */

    @RequestMapping(value="/testupload", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file) {

        String property = System.getProperty("user.dir");//user.dir指定了当前的路径   项目绝对路径
        //拼接文件名  全路径地址   注意这里文件路径      不能使用 /   要是有\   进行转义 所以是 \\
        String filePath = property+"\\file\\";
        // 先判断文件是否为空
        if (!file.isEmpty()){
            // 获得原始文件名
            String fileName = file.getOriginalFilename();
            // 重命名文件   时间戳加文件名   2.jpg
            String newfileName = new Date().getTime() + String.valueOf(fileName);
            // 创建文件实例   （文件地址）
            File tempFile = new File(filePath);
           // 判断父级目录是否存在，不存在则创建
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdir();
            }
            // 判断文件是否存在，否则创建文件（夹）
            if (!tempFile.exists()) {
                tempFile.mkdir();
            }
            try {
                //创建对象  文件全限定名
                File tempFile1 = new File(filePath+newfileName);
                //通过引入的conmon-io jar包内的工具类  保存文件到目录中
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile1);
                return "上传文件成功";
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            return "文件为空！";
        }
        //返回json
        return "上传失败！";
    }
}
