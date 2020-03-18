package com.dxg.msns.sms.listener;

import com.dxg.msns.sms.service.SmsService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class SmsEmailListener {
    @Autowired
    private SmsService smsService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "msns.sms.email.queue",durable = "true"),
            exchange = @Exchange(value = "msns.sms.email.exchange",ignoreDeclarationExceptions = "true"),
            key = {"sms.verify.code"}
    ))
    public void listenSms(Map<String,String> msg){
        if (msg == null || msg.size()<=0){
            return;
        }
        String email = msg.get("email");
        String code = msg.get("code");
        if (StringUtils.isEmpty(email)||StringUtils.isEmpty(code)){
            return;
        }
        Boolean success = smsService.sendMail(email, code);
    }
}
