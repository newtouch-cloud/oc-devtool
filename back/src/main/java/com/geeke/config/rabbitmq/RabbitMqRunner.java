package com.geeke.config.rabbitmq;

import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * RabbitMq启动监听配置类
 * spring.rabbitmq.enable=false，不启动消息监听； =true启动监听
 * @author lys
 * version: 2021/11/16
 */
@Component
public class RabbitMqRunner implements ApplicationRunner {
	@Value("${spring.rabbitmq.enable: false}")
	private boolean enableMq;		// 是否启用mq
	
    private final RabbitListenerEndpointRegistry registry;
 
    @Autowired
    public RabbitMqRunner(RabbitListenerEndpointRegistry registry) {
       this.registry =  registry;
    }
 
    @Override
    public void run(ApplicationArguments args) {
        //执行一些数据初始化代码（省略）
        //得到容器的对象
        MessageListenerContainer container = registry.getListenerContainer("directActionLogHandlerId");
        //判断容器状态和配置是否启用mq
        if(!container.isRunning() && enableMq){
            //开启容器
            container.start();
            System.out.println("开启容器");
        }
        
        container = registry.getListenerContainer("topicActionLogHandlerId");
        //判断容器状态和配置是否启用mq
        if(!container.isRunning() && enableMq){
            //开启容器
            container.start();
            System.out.println("开启容器");
        }

         container  = registry.getListenerContainer("noticeSendHandlerId");
            if(!container.isRunning() && enableMq){
                //开启容器
                container.start();
                System.out.println("开启容器");
            }


    }

	public boolean isEnableMq() {
		return enableMq;
	}

	public void setEnableMq(boolean enableMq) {
		this.enableMq = enableMq;
	}
    
    
}

