package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Reply;

public interface ReplyService {

	Reply replyCount(Reply reply);

	List<Reply> replyMain(Reply reply);

	

}
