package com.dxg.msns.auth.service;

import com.dxg.msns.auth.entity.UserInfo;
import com.dxg.msns.user.pojo.User;

public interface AuthService {
    /**
     * 验证用户,返回token
     * @param uname
     * @param upassword
     * @return
     */
    String authentication(String uname, String upassword);

    /**
     * 根据token中的用户信息获取用户详细信息
     * @param userInfo
     * @return
     */
    User getUser(UserInfo userInfo);

    /**
     * 修改密码
     * @param upassword
     * @param newPassword
     * @param id
     */
    void changePwd(String upassword, String newPassword, Integer id);
}
