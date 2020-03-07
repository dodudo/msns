package com.dxg.msns.upload.service.impl;

import com.dxg.msns.upload.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    private static final List<String> IMAGE_TYPES = Arrays.asList("image/jpeg","image/png","image/jpg", "image/gif");
    private static final String MUSIC_TYPES = "audio";
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
    @Override
    public String upload(MultipartFile file) {
        //校验文件类型
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (!IMAGE_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法：{}",originalFilename);
            return null;
        }
        //校验文件内容
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                LOGGER.info("文件类型不合法：{}",originalFilename);
                return null;
            }
            String fileName = "D:\\msns\\images\\";
            File localDir = new File(fileName);
            if (!localDir.exists()){
                localDir.mkdirs();
            }
            //保存到服务器
            file.transferTo(new File("D:\\msns\\images\\"+originalFilename));
            return "http://localhost:10000/file/images/"+originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String uploadMusic(MultipartFile file) {
        //校验文件类型
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();

        if (!contentType.contains(MUSIC_TYPES)){
            System.out.println(contentType);
            LOGGER.info("文件类型不合法：{}",originalFilename);
            return null;
        }
        //校验文件内容
        try {
            String fileName = "D:\\msns\\music\\";
            File localDir = new File(fileName);
            if (!localDir.exists()){
                localDir.mkdirs();
            }
            //保存到服务器
            file.transferTo(new File("D:\\msns\\music\\"+originalFilename));
            return "http://localhost:10000/file/music/"+originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
