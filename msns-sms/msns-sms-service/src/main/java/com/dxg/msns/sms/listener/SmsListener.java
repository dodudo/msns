package com.dxg.msns.sms.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SmsListener {
    /**
     * 处理来自exchange为sms的信息，新建队列
     *
     * @param id
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "msns.sms.create.queue", durable = "true"),
            exchange = @Exchange(
                    value = "msns.sms.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"sms.*"}
    ))
    public void listenCreate(String id) throws Exception {
        if (id == null) {
            return;
        }
    }
}
