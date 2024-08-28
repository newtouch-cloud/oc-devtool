package com.geeke.config.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.geeke.sys.entity.Action;
import com.geeke.sys.service.ActionService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @auther wenfeng.song
 * @date 2021-07-28 16:19
 */
@Component
@Slf4j
public class ActionLogReceiver {
	
    @Autowired
    private ActionService actionService;

    @RabbitHandler
    @RabbitListener(id="topicActionLogHandlerId", queues = "topic-queue-actionLog_${spring.application.name}_${spring.profiles.active}", autoStartup = "false")
    public void topicActionLogHandler(String content) {
        System.out.println("topicActionLogHandler接收到：" + content);
    }

    @RabbitHandler
    @RabbitListener(id="directActionLogHandlerId", queues = "direct-queue-actionLog_${spring.application.name}_${spring.profiles.active}", autoStartup = "false")
    public void directActionLogHandler(String jsonAction) {
        log.info("receive message: ==>> {}", jsonAction);
        Action action = JSONObject.parseObject(jsonAction, Action.class);
        actionService.saveMQtoDB(action);
    }
}
