package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ReplyDao;
import com.java501.S20230401.model.Reply;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	private final ReplyDao rd;
	@Override
	public int replyCount(Reply reply) {
		int countReply = 0;
		countReply = rd.replyCount(reply);
		System.out.println("댓글수 서비스 시작"+ countReply);
		return countReply;
	}
	@Override
	public List<Reply> replyList(Reply reply) {

		List<Reply> listReply = null;
		listReply = rd.replyList(reply);
		System.out.println("댓글 서비스 시작"+ listReply);
		return listReply;
	}

}
