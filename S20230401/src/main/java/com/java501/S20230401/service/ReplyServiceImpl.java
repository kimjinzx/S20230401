package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ReplyDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

import kotlin.jvm.internal.SerializedIr;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	private final ReplyDao rd;
	
	
	// 양동균
	@Override
	public List<Reply> replyShareList(Article article) {
		return rd.replyShareList(article);
	}
	@Override
	public int writeReply(Reply reply) {
		return rd.writeReply(reply);
	}
	@Override
	public int deleteReply(Reply reply) {
		return rd.deleteReply(reply);
	}
	
	
	// 백준
		@Override
	public Reply replyCount(Reply reply) {
		return rd.replyCount(reply);
	}
	@Override
	public List<Reply> replyMain(Reply reply) {
		return rd.replyMain(reply);
		
	}
}
