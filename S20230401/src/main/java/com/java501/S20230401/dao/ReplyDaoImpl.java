//package com.java501.S20230401.dao;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.stereotype.Repository;
//
//import com.java501.S20230401.model.Article;
//
//import lombok.RequiredArgsConstructor;
//
//@Repository
//@RequiredArgsConstructor
//public class ReplyDaoImpl implements ReplyDao {
//
//	private final SqlSession session;
//	
//	@Override
//	public int insertReply(Article article) {
//		int insertReply = 0;
//		try {
//			insertReply = session.insert("DBinsertReply", article);
//		} catch (Exception e) {
//			System.out.println("insertReplyDaoImpl Exception = " + e.getMessage());
//		}
//		return insertReply;
//	}
//
//}
