package com.dxg.msns.upload.controller;

import com.dxg.msns.auth.entity.UserInfo;
import com.dxg.msns.auth.utils.CookieUtils;
import com.dxg.msns.auth.utils.JwtUtils;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("image")
    public ResponseEntity<String> uploadImage( @RequestParam("file") MultipartFile file, HttpServletRequest request,HttpServletResponse response){
        String url = this.uploadService.upload(file);
        if (StringUtils.isBlank(url)) {
            return ResponseEntity.badRequest().build();
        }
//        CookieUtils.setCookie(request,response,"MSNS_TOKEN",token,30,null,true);
        return ResponseEntity.ok(url);
    }

    @PostMapping("music")
    public ResponseEntity<Music> uploadMusic(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpServletResponse response){
        Music music = this.uploadService.uploadMusic(file);
        if (music==null) {
            return ResponseEntity.badRequest().build();
        }
//        CookieUtils.setCookie(request,response,"MSNS_TOKEN",token,30,null,true);

        return ResponseEntity.ok(music);
    }
}
