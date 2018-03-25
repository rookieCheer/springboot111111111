package com.lyf.springboot03.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/02")
public class FileController02 {
    private static final Logger logger = LoggerFactory.getLogger(FileController02.class);
    //跳转到上传文件的页面
    @RequestMapping(value = "/goUploadHtml", method = RequestMethod.GET)
    public String goUploadHtml() {
        //跳转到 templates 目录下的 upload.html
        return "upload02";
    }
    /**
     *   多文件上传
     * @RequestParam("file")中的file对应input标签中的name属性值
     */

    @RequestMapping(value="/testupload", method = RequestMethod.POST)
    public @ResponseBody Map<String ,Object> uploadImg(MultipartHttpServletRequest request) {
        Map<String ,Object> map = new HashMap<>();
       //前端页面name名    .getFiles("name名0");
        List<MultipartFile> files = request.getFiles("file");
        //user.dir指定了当前的路径   项目绝对路径
        String property = System.getProperty("user.dir");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        logger.info("项目路径："+property+"文件开始上传----------");
        //对 List<MultipartFile> 集合进行便利取出MultipartFile对象
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    logger.info("图片全路径："+property+"/file/"+file.getOriginalFilename());
                    stream = new BufferedOutputStream(new FileOutputStream(
                                    new File(property+"/file/"+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    logger.info("上传图片失败！  " + i + " => "+ e.getMessage());
                    map.put("info","上传图片失败！  " + i + " => "
                            + e.getMessage());
                    return map ;
                }
                //提示那个文件上传成功
                map.put("info"+ i,"上传  " + i + "成功！") ;
            } else {
                //提示那个文是空的
               map.put("info"+ i,"上传失败！  " + i + "文件是空的！") ;
            }
        }
        return map;
    }


    /**
     * 文件下载
     * @param path   文件全路径
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "getImage")
    @ResponseBody
    public String getIcon(@RequestParam(value = "path", required = true) String path,HttpServletResponse response)
            throws IOException {
        logger.info("要获取的文件全路径："+path);
        String fileName = path;
        //创建文件对象
        File file = new File(fileName);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        int length = inputStream.read(data);
        inputStream.close();
        //response.setContentType("image/png");
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
        return "成功！";
    }


}
