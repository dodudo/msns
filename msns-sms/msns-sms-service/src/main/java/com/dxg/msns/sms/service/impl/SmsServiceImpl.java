package com.dxg.msns.sms.service.impl;

import com.dxg.msns.sms.service.SmsService;
import com.dxg.msns.sms.util.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsUtils smsUtils;
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public Boolean sendMail(String toAddress, String code) {
        SimpleMailMessage simpleMailMessage = smsUtils.mailModule(toAddress, code);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
