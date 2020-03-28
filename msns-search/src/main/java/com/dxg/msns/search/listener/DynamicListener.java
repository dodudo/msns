package com.dxg.msns.search.listener;

import com.dxg.msns.search.service.DynamicSearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicListener {
    @Autowired
    private DynamicSearchService dynamicSearchService;

    /**处理insert和update的信息
     *
     * @param id
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "msns.create.index.queue",durable = "true"),
            exchange = @Exchange(
                    value = "msns.dynamic.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"dynamic.insert","dynamic.update"}
    ))
    public void listenCreate(Integer id)throws Exception{
        if (id == null){
            return;
        }
        this.dynamicSearchService.createDynamicIndex(id);
    }

    /**
     * 处理delete信息
     * @param id
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "msns.delete.index.queue",durable = "true"),
            exchange = @Exchange(
                    value = "msns.dynamic.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"dynamic.delete"}
    ))
    public void listenDelete(Integer id)throws Exception{
        if (id == null){
            return;
        }
        this.dynamicSearchService.deleteDynamicIndex(id);
    }

}
