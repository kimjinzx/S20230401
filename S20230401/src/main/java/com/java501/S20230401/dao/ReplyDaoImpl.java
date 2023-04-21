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
	public int replyCount(Reply reply) {
		System.out.println("댓글수 다오 시작");
		int countReply = 0;
		
		try {
			countReply = session.selectOne("shcountReply", reply);
		} catch (Exception e) {
			System.out.println("댓글수에러"+e.getMessage());
		}
		
		return countReply;
	}

	@Override
	public List<Reply> replyList(Reply reply) {
		System.out.println("댓글 다오 시작");
		
		List<Reply> listReply = null;
		
		try {
			listReply = session.selectList("shlistReply",reply);
		} catch (Exception e) {
			System.out.println("댓글에러"+e.getMessage());
		}
		return listReply;
	}

}
