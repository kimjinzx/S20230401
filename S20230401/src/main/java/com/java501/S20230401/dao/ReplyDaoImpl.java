package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Reply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDaoImpl implements ReplyDao {
	private final SqlSession session;
	
	@Override
	public Reply replyCount(Reply reply) {
		Reply countReply = null;
		try {
			countReply = session.selectOne("bjCountReply",reply);
		} catch (Exception e) {
			System.out.println("댓글수에러"+e.getMessage());
		}
		return countReply;
	}

	@Override
	public List<Reply> replyMain(Reply reply) {
		List<Reply> mainReply = null;
		try {
			mainReply = session.selectList("bjreplyMain",reply);
		} catch (Exception e) {
			System.out.println("댓글에러"+e.getMessage());
		}
		
		return mainReply;
	}


}
