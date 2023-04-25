package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

public interface ReplyService {

	List<Reply> replyShareList(Article article);
	int writeReply(Reply reply);
	int deleteReply(Reply reply);

}
