package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

@Repository
public interface ReplyDao {

	List<Reply> replyShareList(Article article);
	int writeReply(Reply reply);
	int deleteReply(Reply reply);

}
