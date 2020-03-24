package com.dxg.msns.search.listener;

import com.dxg.msns.search.client.DynamicsClient;
import com.dxg.msns.search.client.UserClient;
import com.dxg.msns.search.service.DynamicSearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
    @Autowired
    private DynamicSearchService dynamicSearchService;

    @Autowired
    private DynamicsClient dynamicsClient;


}
