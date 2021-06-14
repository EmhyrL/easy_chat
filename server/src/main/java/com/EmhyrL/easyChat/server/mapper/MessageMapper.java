package com.EmhyrL.easyChat.server.mapper;

import com.EmhyrL.easyChat.server.model.Message;
import com.EmhyrL.easyChat.server.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends MyMapper<Message> {
}