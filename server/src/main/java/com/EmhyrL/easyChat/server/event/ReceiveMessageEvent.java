package com.EmhyrL.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.EmhyrL.easyChat.common.action.Action;
import com.EmhyrL.easyChat.common.action.ReceiveMessageNotifyAckAction;
import com.EmhyrL.easyChat.common.action.ReceiveMessageNotifyAction;
import com.EmhyrL.easyChat.common.event.IEvent;
import com.EmhyrL.easyChat.server.model.Message;
import com.EmhyrL.easyChat.server.service.MessageService;
import com.EmhyrL.easyChat.server.util.SpringContextUtil;
import io.netty.channel.Channel;

public class ReceiveMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {

        System.out.println("received action: " + action);
        ReceiveMessageNotifyAckAction notifyAckAction = JSONObject.parseObject(action.getPayload(), ReceiveMessageNotifyAckAction.class);
        System.out.println("received message ack: " + notifyAckAction);


        MessageService messageService = SpringContextUtil.getBean(MessageService.class);
        Message message = messageService.getById(notifyAckAction.getMessageId());
        if ( null == message ) {
            System.out.println("no message found with messageId: " + notifyAckAction.getMessageId());
            return null;
        }
        if ( message.getStatus() != 0 ) {
            System.out.println("message status is not 0. status: " + message.getStatus());
            return null;
        }

        // 更新数据库
        message.setStatus((byte) 1);
        message.setRecvTimestamp(System.currentTimeMillis());
        messageService.modify(message);
        return null;
    }

}
