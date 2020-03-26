package com.dxg.msns.upload.service.impl;

import com.dxg.msns.common.util.UUIDUtils;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.upload.service.UploadService;
import com.dxg.msns.upload.utils.GetMusicInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.audio.mp3.MP3FileReader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private GetMusicInfo getMusicInfo;

    private static final List<String> IMAGE_TYPES = Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/gif");
    private static final List<String> MUSIC_TYPES = Arrays.asList("audio/mp3", "audio/flac", "audio/wav", "audio/ogg","audio/x-mp3", "audio/x-flac", "audio/x-wav", "audio/x-ogg");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    @Override
    public String upload(MultipartFile[] file) {
        StringBuilder stringBuilder =  new StringBuilder();
        for (int i = 0;i<file.length;i++){
            //校验文件类型
            String originalFilename = file[i].getOriginalFilename();
            String contentType = file[i].getContentType();
            String prefix = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (!IMAGE_TYPES.contains(contentType)) {
                LOGGER.info("文件类型不合法：{}", originalFilename);
                return null;
            }
            //校验文件内容
            try {
                BufferedImage bufferedImage = ImageIO.read(file[i].getInputStream());
                if (bufferedImage == null) {
                    LOGGER.info("文件类型不合法：{}", originalFilename);
                    return null;
                }
                String fileName = "D:\\msns\\images\\";
                File localDir = new File(fileName);
                if (!localDir.exists()) {
                    localDir.mkdirs();
                }
                String newFileName = UUIDUtils.getUUID()+prefix;
                //保存到服务器
                file[i].transferTo(new File("D:\\msns\\images\\" + newFileName));
                //保存服务器路径
                if (file.length == 1 || i==file.length-1){
                    stringBuilder.append( "http://localhost:10000/file/images/" + newFileName);
                }else {
                    stringBuilder.append( "http://localhost:10000/file/images/" + newFileName+",");
                }
            } catch (IOException e) {
                LOGGER.info("服务器内部错误：{}", originalFilename);
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public Music uploadMusic(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }
        //校验文件类型
        String originalFilename = multipartFile.getOriginalFilename();
        //multipartFile转File
        String prefix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File tempFile = null;
        Music music = new Music();
        try {
            tempFile = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), prefix);
            multipartFile.transferTo(tempFile);  //转换为File类型
            //获取上传文件类型
            String contentType = multipartFile.getContentType();
            System.out.println(contentType);
            if (!MUSIC_TYPES.contains(contentType)){
                LOGGER.info("文件类型不合法：{}", originalFilename);
                return null;
            }
            music = getMusicInfo.getMusicTags(tempFile,contentType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //校验文件内容
        try {
            String fileName = "D:\\msns\\music\\";
            File localDir = new File(fileName);
            if (!localDir.exists()) {
                localDir.mkdirs();
            }
            //保存到服务器
            FileUtils.copyFile(tempFile, new File("D:\\msns\\music\\" + originalFilename));
            String musicNetPath = "http://localhost:10000/file/music/" + originalFilename;
            music.setMusicUrl(musicNetPath);
            System.out.println(music);
            return music;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
