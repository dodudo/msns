package com.dxg.msns.auth.controller;

import com.dxg.msns.auth.entity.UserInfo;
import com.dxg.msns.auth.properties.JwtProperties;
import com.dxg.msns.auth.service.AuthService;
import com.dxg.msns.auth.utils.CookieUtils;
import com.dxg.msns.auth.utils.JwtUtils;
import com.dxg.msns.user.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("accredit")
    public ResponseEntity<Void> authentication(@RequestParam("uname") String uname,
                                               @RequestParam("upassword") String upassword,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
        //登录校验，生成token
        String token = this.authService.authentication(uname,upassword);
        if (StringUtils.isEmpty(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //将token写入cookie，并指定httpOnly为true，防止js获取修改
        CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),token,this.jwtProperties.getCookieMaxAge(),null,true);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("deleteCookie")
    public ResponseEntity<Void> deleteCookie(HttpServletRequest request,HttpServletResponse response) {
        CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),null,0,null,true);
        return ResponseEntity.ok().build();
    }

    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("MSNS_TOKEN")String token,HttpServletRequest request,HttpServletResponse response){
//        System.out.println("token:::"+token);
        try {
            //从token‘中解析信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            User user = authService.getUser(userInfo);
            userInfo.setAvatarUrl(user.getAvatarUrl());
            userInfo.setId(user.getId());
            userInfo.setUid(user.getUid());
            userInfo.setUname(user.getUname());
            //解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
            //更新cookie中的token
            CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),token,this.jwtProperties.getCookieMaxAge(),null,true);
            //解析成功返回用户信息
            return  ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("getUser")
    public ResponseEntity<User> getUser(@CookieValue("MSNS_TOKEN")String token, HttpServletRequest request, HttpServletResponse response){
//        System.out.println("token:::"+token);
        try {
            //从token‘中解析信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            User user = authService.getUser(userInfo);
            //解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
            //更新cookie中的token
            CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),token,this.jwtProperties.getCookieMaxAge(),null,true);
            //解析成功返回用户信息
            return  ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("changePwd")
    public ResponseEntity<Void> changeUserPwd(@CookieValue("MSNS_TOKEN")String token,
                                              @RequestParam("upassword") String upassword,
                                              @RequestParam("newPassword") String newPassword,
                                              HttpServletRequest request,
                                              HttpServletResponse response){
//        System.out.println("token:::"+token);
        try {
            //从token‘中解析信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            authService.changePwd(upassword,newPassword,userInfo.getId());
            //解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
            //更新cookie中的token
            CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),token,this.jwtProperties.getCookieMaxAge(),null,true);
            //解析成功返回用户信息
            return  new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

