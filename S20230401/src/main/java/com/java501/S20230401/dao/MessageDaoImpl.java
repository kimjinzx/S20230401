package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Message;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MessageDaoImpl implements MessageDao {
	private final SqlSession session;

	// 양동균
	// 받은 쪽지
	@Override
	public List<Message> dgMessageListRec(Integer mem_id) {
		List<Message> messageList = null;
		try {
			messageList = session.selectList("dgMessageListRec", mem_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageList;
	}
	// 보낸 쪽지
	@Override
	public List<Message> dgMessageListSen(Integer mem_id) {
		List<Message> messageList = null;
		try {
			messageList = session.selectList("dgMessageListSen", mem_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageList;
	}
	@Override
	public void dgMessageWrite(Message message) {
		try {
			session.insert("dgMessageWrite", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
