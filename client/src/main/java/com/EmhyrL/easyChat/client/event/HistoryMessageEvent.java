package com.EmhyrL.easyChat.client.event;

import com.alibaba.fastjson.JSONObject;
import com.EmhyrL.easyChat.common.action.Action;
import com.EmhyrL.easyChat.common.action.FetchHistoryMessageRespAction;
import com.EmhyrL.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class HistoryMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        FetchHistoryMessageRespAction respAction = JSONObject.parseObject(action.getPayload(), FetchHistoryMessageRespAction.class);
        System.out.println("received history message: " + respAction);
        return null;
    }
}
