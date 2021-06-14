package com.EmhyrL.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.EmhyrL.easyChat.common.action.Action;
import com.EmhyrL.easyChat.common.action.LoginReqAction;
import com.EmhyrL.easyChat.common.action.LoginRespAction;
import com.EmhyrL.easyChat.common.event.IEvent;
import com.EmhyrL.easyChat.server.connection.ConnectionPool;
import com.EmhyrL.easyChat.server.model.User;
import com.EmhyrL.easyChat.server.service.UserService;
import com.EmhyrL.easyChat.server.util.SpringContextUtil;
import io.netty.channel.Channel;

/**
 * 接收登陆结果
 */
public class LoginEvent implements IEvent<Action, Action> {


    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("receive action: " + action);
        LoginReqAction reqAction = JSONObject.parseObject(action.getPayload(), LoginReqAction.class);
        System.out.println("received login action req: " + reqAction);

        UserService userService = SpringContextUtil.getBean(UserService.class);
        if ( null == userService ) {
            System.out.println("can not get userService from ioc");
            return null;
        }

        LoginRespAction respAction = new LoginRespAction();
        respAction.setResult(false);
        User user = userService.find(reqAction.getMobile(), reqAction.getPassword());
        if ( null == user ) {
            System.out.println("user not found with mobile: " + reqAction.getMobile() + " and password: " + reqAction.getPassword());
            respAction.setPayload(JSONObject.toJSONString(respAction));
            return respAction;
        }
        // 添加到链接池
        System.out.println("login success with mobile: " + user.getMobile());
        ConnectionPool.getInstance().add(user.getId(), channel);
        // 返回数据
        respAction.setUserId(user.getId());
        respAction.setResult(true);
        respAction.setPayload(JSONObject.toJSONString(respAction));
        return respAction;
    }

}
