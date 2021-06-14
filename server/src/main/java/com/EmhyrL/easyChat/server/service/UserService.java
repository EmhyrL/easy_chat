package com.EmhyrL.easyChat.server.service;

import com.EmhyrL.easyChat.server.mapper.UserMapper;
import com.EmhyrL.easyChat.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据手机号和密码查找用户
     * @param mobile
     * @param password
     * @return
     */
    public User find(final String mobile, final String password) {
        if ( StringUtils.isEmpty(mobile) ) {
            System.out.println("mobile is empty!");
            return null;
        }
        if ( StringUtils.isEmpty(password) ) {
            System.out.println("password is empty!");
            return null;
        }

        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo(User.MOBILE, mobile)
                .andEqualTo(User.PASSWORD, password);

        List<User> clients = userMapper.selectByExample(example);
        if ( CollectionUtils.isEmpty(clients) ) {
            System.out.println("user not found with mobile: " + mobile + " and password: " + password);
            return null;
        }
        if ( clients.size() != 1 ) {
            System.out.println("user duplicated with mobile: " + mobile + " and password: " + password);
            return null;
        }
        return clients.get(0);
    }

    /**
     * 获取所有用户
     * @param userIds
     * @return
     */
    public List<User> listUsers(final List<Long> userIds) {
        if ( CollectionUtils.isEmpty(userIds) ) {
            System.out.println("userIds is empty!");
            return null;
        }
        Example example = new Example(User.class);
        example.createCriteria().andIn(User.ID, userIds);
        return userMapper.selectByExample(example);
    }

    public User getById(final Long userId) {
        if ( null == userId ) {
            System.out.println("empty userId");
            return null;
        }
        return userMapper.selectByPrimaryKey(userId);
    }

}
