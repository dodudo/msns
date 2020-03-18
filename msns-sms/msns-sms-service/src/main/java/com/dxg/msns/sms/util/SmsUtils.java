package com.dxg.msns.sms.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils {
    /**
     * 发送邮箱模板
     * @param toAddress
     * @param code
     * @return
     */
    public SimpleMailMessage mailModule(String toAddress,String code){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //设置标题
        simpleMailMessage.setSubject("【MSNS-音乐社交网站】"+toAddress+";欢迎您注册本音乐社交网站！");
        //设置发送者邮箱
        simpleMailMessage.setFrom("18384354901@163.com");
        //设置发送目标邮箱
        simpleMailMessage.setTo(toAddress);
        //设置验证码
        simpleMailMessage.setText("【MSNS-音乐社交网站】"+toAddress+",您的验证码为："+code+";验证码五分钟内有效！");
        return simpleMailMessage;
    }
}
