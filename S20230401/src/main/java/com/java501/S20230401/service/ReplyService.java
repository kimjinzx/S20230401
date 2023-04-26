package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

public interface ReplyService {
	

	// 양동균
	List<Reply> replyShareList(Article article);
	int writeReply(Reply reply);
	int deleteReply(Reply reply);

	// 백준
	Reply replyCount(Reply reply);
	List<Reply> replyMain(Reply reply);
}
