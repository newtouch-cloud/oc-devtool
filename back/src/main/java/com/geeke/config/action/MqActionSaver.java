package com.geeke.config.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geeke.common.IActionSaver;
import com.geeke.config.rabbitmq.ActionLogSender;
import com.geeke.sys.entity.Action;
import com.geeke.sys.entity.ActionRecycle;

/**
 * 日志发送到消息队列，通过消息队列入库
 * @author lys
 * version: 2021/11/16
 */
public class MqActionSaver implements IActionSaver {
    /**
    * 消息队列
    */
   @Autowired
   private ActionLogSender actionLogSender;
   
   
   
	
	@Override
	public void saveAction(Action action) {
		if(action == null) {
			return;
		}
		action.preInsert();
		for (ActionRecycle actionRecycle : action.getActionRecycleList()){
			actionRecycle.preInsert();
		}
		actionLogSender.sendDirect(action);
	}

}
