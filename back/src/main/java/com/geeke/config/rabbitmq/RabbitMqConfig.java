package com.geeke.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther wenfeng.song
 * @date 2021-07-28 13:42
 */
@Configuration
public class RabbitMqConfig {

    // topic mode
    public static String TOPIC_EXCHANGE_ACTION_LOG;
    public static String TOPIC_QUEUE_ACTION_LOG;
    public static String TOPIC_ROUTE_ACTION_LOG;

    // direct mode
    public static String DIRECT_EXCHANGE_ACTION_LOG;
    public static String DIRECT_QUEUE_ACTION_LOG;
    public static String DIRECT_ROUTE_ACTION_LOG;

    //notice
    public static String NOTICE_EXCHANGE_ACTION_LOG;
    public static String NOTICE_QUEUE_ACTION_LOG;
    public static String NOTICE_ROUTE_ACTION_LOG;

    public static String profile;
    @Value(value = "${spring.profiles.active}")
    public void setProfile(String profile) {
        RabbitMqConfig.profile = profile;
        RabbitMqConfig.TOPIC_EXCHANGE_ACTION_LOG = "topic-exchange-actionLog_" + application + "_" + profile;
        RabbitMqConfig.TOPIC_QUEUE_ACTION_LOG = "topic-queue-actionLog_" + application + "_" + profile;
        RabbitMqConfig.TOPIC_ROUTE_ACTION_LOG = "topic-route-actionLog_" + application + "_" + profile;

        RabbitMqConfig.DIRECT_EXCHANGE_ACTION_LOG = "direct-exchange-actionLog_" + application + "_" + profile;
        RabbitMqConfig.DIRECT_QUEUE_ACTION_LOG = "direct-queue-actionLog_" + application + "_" + profile;
        RabbitMqConfig.DIRECT_ROUTE_ACTION_LOG = "direct-route-actionLog_" + application + "_" + profile;

        RabbitMqConfig.NOTICE_EXCHANGE_ACTION_LOG = "notice-exchange-actionLog_"+ application + "_" + profile;
        RabbitMqConfig.NOTICE_QUEUE_ACTION_LOG = "notice-queue-actionLog_"+ application + "_" + profile;
        RabbitMqConfig.NOTICE_ROUTE_ACTION_LOG = "notice-route-actionLog_"+ application + "_" + profile;

    }

    public static String application;
    @Value(value = "${spring.application.name}")
    public void setApplication(String application) {
        RabbitMqConfig.application = application;
    }


    
    
    @Bean
    public TopicExchange topicExchangeActionLog() {
        // 默认情况下，durable为true,auto-delete为false
        return (TopicExchange) ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_ACTION_LOG).durable(true).build();
    }

    @Bean
    public Queue topicQueueActionLog() {
        return QueueBuilder.durable(TOPIC_QUEUE_ACTION_LOG).build();
    }

    @Bean
    public Binding topicBindingActionLog(TopicExchange topicExchangeActionLog, Queue topicQueueActionLog) {
        return BindingBuilder.bind(topicQueueActionLog).to(topicExchangeActionLog).with(TOPIC_ROUTE_ACTION_LOG);
    }

    @Bean
    public DirectExchange directExchangeActionLog() {
        // 默认情况下，durable为true,auto-delete为false
        return (DirectExchange) ExchangeBuilder.directExchange(DIRECT_EXCHANGE_ACTION_LOG).durable(true).build();
    }

    @Bean
    public Queue directQueueActionLog() {
//        return new Queue(DIRECT_QUEUE_NAME_1);//默认情况，durable为true,exclusive为false,auto-delete为false
        return QueueBuilder.durable(DIRECT_QUEUE_ACTION_LOG).build();
    }

    @Bean
    public Binding directBindingActionLog(DirectExchange directExchangeActionLog, Queue directQueueActionLog) {
        return BindingBuilder.bind(directQueueActionLog).to(directExchangeActionLog).with(DIRECT_ROUTE_ACTION_LOG);
    }

    // 消息发送

    @Bean
    public DirectExchange noticeSendExchange() {
        // 默认情况下，durable为true,auto-delete为false
        return (DirectExchange) ExchangeBuilder.directExchange(NOTICE_EXCHANGE_ACTION_LOG).durable(true).build();
    }

    @Bean
    public Queue noticeSendQueue() {
//        return new Queue(DIRECT_QUEUE_NAME_1);//默认情况，durable为true,exclusive为false,auto-delete为false
        return QueueBuilder.durable(NOTICE_QUEUE_ACTION_LOG).build();
    }

    @Bean
    public Binding noticeSendQueueBinding(DirectExchange noticeSendExchange, Queue noticeSendQueue) {
        return BindingBuilder.bind(noticeSendQueue).to(noticeSendExchange).with(NOTICE_ROUTE_ACTION_LOG);
    }





}
