package com.dxg.msns.auth.service.impl;

import com.dxg.msns.auth.client.UserClient;
import com.dxg.msns.auth.entity.UserInfo;
import com.dxg.msns.auth.properties.JwtProperties;
import com.dxg.msns.auth.service.AuthService;
import com.dxg.msns.auth.utils.JwtUtils;
import com.dxg.msns.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public String authentication(String uname, String upassword) {
        //如果有查询结果
        try {
            User user = this.userClient.login(uname, upassword);
            //如果查询为空，直接返回为null
            if (user == null) {
                return null;
            }
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUid(), user.getUname()), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
