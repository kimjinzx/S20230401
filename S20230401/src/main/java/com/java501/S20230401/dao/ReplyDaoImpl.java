package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.ReplyMember;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDaoImpl implements ReplyDao {
	private final SqlSession session;
	
	// 양동균
	// 댓글 리스트
	@Override
	public List<Reply> replyShareList(Article article) {
		List<Reply> replyList = null;
		try {
			replyList = session.selectList("gdReplyShareList", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyList;
	}
	// 댓글 작성
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
	// 댓글 삭제
	@Override
	public int deleteReply(Reply reply) {
		int result = 0;
		try {
			result = session.update("dgDeleteReply", reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	// 백준
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
	
	
	
	
	
	// 최승환
	@Override
	public int shReplyCount(Reply reply) {
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
	
	// 유현규
	@Override
	public List<ReplyMember> getReplyByArticle(Article article) {
		return session.selectList("hgGetReplyByArticle", article);
	}
	
	@Override
	public int hgInsertReply(Reply reply) {
		return session.insert("hgInsertReply", reply);
	}
	
	// 김진현
	@Override
	public int replyInsert2(Article article) {
		return session.insert("JHReplyInsert", article);
	}
	@Override
	public int replyDelete2(Article article) {
		return session.update("JHReplyUpdate", article);		
	}	
}
