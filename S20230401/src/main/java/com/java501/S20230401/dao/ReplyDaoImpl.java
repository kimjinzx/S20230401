package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDaoImpl implements ReplyDao {
	private final SqlSession session;
	
	@Override
	public List<Reply> replyList(Article article) {
		List<Reply> replyList = null;
		try {
			replyList = session.selectList("gdReplyList", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyList;
	}

	@Override
	public int writeReply(Reply reply) {
		int reulst = 0;
		try {
			reulst = session.insert("dgWriteReply", reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reulst;
	}

}
