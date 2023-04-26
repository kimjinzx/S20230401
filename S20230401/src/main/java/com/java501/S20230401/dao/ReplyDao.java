package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.ReplyMember;

public interface ReplyDao {

	// 양동균
	List<Reply> replyShareList(Article article);
	int writeReply(Reply reply);
	int deleteReply(Reply reply);
	
	
	// 백준
	Reply replyCount(Reply reply);
	List<Reply> replyMain(Reply reply);
	
	
	// 최승환
	int 	shReplyCount(Reply reply);
	List<Reply> replyList(Reply reply);
	
	
	// 유현규
	public List<ReplyMember> getReplyByArticle(Article article);
	public int hgInsertReply(Reply reply);
}

