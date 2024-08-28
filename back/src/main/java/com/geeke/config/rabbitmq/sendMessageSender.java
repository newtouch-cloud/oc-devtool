package com.geeke.config.rabbitmq;
import com.alibaba.fastjson.JSONObject;
import com.geeke.notice.entity.NoticeSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class sendMessageSender{

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendInfo(NoticeSend noticeSend) {
        String id = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(id);
        log.info("message sent: ==>> \n唯一标识: {} \n消息内容{}", id, JSONObject.toJSONString(noticeSend));
        rabbitTemplate.convertAndSend(RabbitMqConfig.NOTICE_EXCHANGE_ACTION_LOG, RabbitMqConfig.NOTICE_ROUTE_ACTION_LOG, JSONObject.toJSONString(noticeSend), correlationData);
    }

}
