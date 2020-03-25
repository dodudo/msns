package com.dxg.msns.search.listener;

import com.dxg.msns.search.service.DynamicSearchService;
import com.dxg.msns.search.service.MusicSearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicListener {
    @Autowired
    private MusicSearchService musicSearchService;

    /**处理insert和update的信息
     *
     * @param id
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "msns.music.create.index.queue",durable = "true"),
            exchange = @Exchange(
                    value = "msns.music.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"music.insert","music.update"}
    ))
    public void listenCreate(Integer id)throws Exception{
        if (id == null){
            return;
        }
        this.musicSearchService.createMusicIndex(id);
    }

    /**
     * 处理delete信息
     * @param id
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "msns.music.delete.index.queue",durable = "true"),
            exchange = @Exchange(
                    value = "msns.music.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"music.delete"}
    ))
    public void listenDelete(Integer id)throws Exception{
        if (id == null){
            return;
        }
        this.musicSearchService.deleteMusicIndex(id);
    }
}
