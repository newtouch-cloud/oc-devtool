package com.geeke.config.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.geeke.notice.entity.NoticeSend;
import com.geeke.notice.service.NoticeSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class sendMessageReceiver {

    @Autowired
    NoticeSendService  sendService;


    @RabbitHandler
    @RabbitListener(id="noticeSendHandlerId", queues = "notice-queue-actionLog_${spring.application.name}_${spring.profiles.active}", autoStartup = "false")
    public void setMessage(String jsonAction) {
        log.info("receive message: ==>> {}", jsonAction);
        try {
            // 延迟10秒发送
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NoticeSend noticeSend = JSONObject.parseObject(jsonAction, NoticeSend.class);
        sendService.sendInfo(noticeSend);
    }

}
