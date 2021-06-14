package com.EmhyrL.easyChat.client.event;

import com.alibaba.fastjson.JSONObject;
import com.EmhyrL.easyChat.common.action.Action;
import com.EmhyrL.easyChat.common.action.ReceiveMessageNotifyAckAction;
import com.EmhyrL.easyChat.common.action.ReceiveMessageNotifyAction;
import com.EmhyrL.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class ReceiveMessageEvent implements IEvent<Action, ReceiveMessageNotifyAckAction> {

    @Override
    public ReceiveMessageNotifyAckAction handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        ReceiveMessageNotifyAction notifyAction = JSONObject.parseObject(action.getPayload(), ReceiveMessageNotifyAction.class);
        System.out.println("received message: " + notifyAction);
        ReceiveMessageNotifyAckAction ackAction = new ReceiveMessageNotifyAckAction();
        ackAction.setMessageId(notifyAction.getMessageId());
        ackAction.setPayload(JSONObject.toJSONString(ackAction));
        return ackAction;
    }

}
