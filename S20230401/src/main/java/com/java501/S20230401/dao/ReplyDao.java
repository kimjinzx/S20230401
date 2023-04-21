package com.java501.S20230401.dao;


import java.util.List;

import com.java501.S20230401.model.Reply;

public interface ReplyDao {

	int 	replyCount(Reply reply);

	List<Reply> replyList(Reply reply);
	

}
