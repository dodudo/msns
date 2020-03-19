package com.dxg.msns.auth.test;

import com.dxg.msns.auth.entity.UserInfo;
import com.dxg.msns.auth.utils.JwtUtils;
import com.dxg.msns.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;


public class JwtTest {

    private static final String pubKeyPath = "D:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @org.junit.Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20, "01","jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVuYW1lIjoiamFjayIsInVpZCI6IjAxIiwiZXhwIjoxNTg0NjAyOTIyfQ.FRYlKLKekYPfpDzz5LpyMzepkWqVLBANbiL_o8GeWwJXzCkDdTR5JJRazEzeQZuzFuvxMfKLQM17ZvVjJlmJPAzn7EtAqt6co4yt3IIa1iMz4JRJ_IUgRJ1vctjAYH61r4bhbjZgtlKsAu-bIxmfaqhO5AxmC-rQXF4npPnl9V0";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("uid:"+user.getUid());
        System.out.println("userName: " + user.getUname());
    }
}