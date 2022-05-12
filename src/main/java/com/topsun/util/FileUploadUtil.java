package com.topsun.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @description 文件上传工具类
 * @author: wzd
 * @create: 2021-11-25 17:00
 * @Version 1.0
 **/
public class FileUploadUtil {

    public static String fileUpload(MultipartFile file, String uploadPath) throws IOException {

            String fileName = UUID.randomUUID().toString().replace("-", "").toUpperCase()+".jpg";
            File targetFile = new File(uploadPath + fileName);
            if(!targetFile.exists()){
                targetFile.getParentFile().mkdirs();
                targetFile.createNewFile();
            }
            try {
                file.transferTo(targetFile);
                uploadPath = targetFile.getAbsolutePath();
            }catch (Exception e){
                e.printStackTrace();
                targetFile.delete();
                return null;
            }
        return uploadPath;
    }
}
