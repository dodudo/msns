package com.dxg.msns.auth.service;

public interface AuthService {
    /**
     * 验证用户,返回token
     * @param uname
     * @param upassword
     * @return
     */
    String authentication(String uname, String upassword);
}
