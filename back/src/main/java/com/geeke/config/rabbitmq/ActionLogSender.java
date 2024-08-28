package com.geeke.config.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.geeke.sys.entity.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate.*;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @auther wenfeng.song
 * @date 2021-07-28 15:05
 */
@Component
@Slf4j
public class ActionLogSender implements ConfirmCallback, ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {//在构造方法后执行
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    public void sendTopic(String selector) {
        String id = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(id);
        log.info("message sent: ==>> \n唯一标识: {} \n消息内容{}", id, selector);

        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_ACTION_LOG, RabbitMqConfig.TOPIC_ROUTE_ACTION_LOG, selector, correlationData);
    }

    public void sendDirect(Action action) {
        String id = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(id);
        log.info("message sent: ==>> \n唯一标识: {} \n消息内容{}", id, JSONObject.toJSONString(action));
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE_ACTION_LOG, RabbitMqConfig.DIRECT_ROUTE_ACTION_LOG, JSONObject.toJSONString(action), correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.info("[RabbitMQ 消息发送结果:] 成功！唯一标识: {} ", correlationData.getId());
        }else {
            log.error("[RabbitMQ 消息发送结果:] 失败！ \n唯一标识: {} \n失败原因: {}", correlationData.getId(), cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息推送失败：\n消息内容为==》{} \n回应码：==》{} \n回应信息：==》{} \n交换机：==》{} \n路由键：==》{}", message, replyCode, replyText, exchange, routingKey);
    }
}
