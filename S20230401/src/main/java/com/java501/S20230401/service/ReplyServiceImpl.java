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
	public Reply replyCount(Reply reply) {
//		Reply replyCount = new Reply();
//		replyCount = rd.replyCount(reply);
//		return replyCount;
		return rd.replyCount(reply);
	}

	@Override
	public List<Reply> replyMain(Reply reply) {

		return rd.replyMain(reply);	
		
		
		
	}
}
