package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Reply;

@Repository
public interface ReplyDao {

	Reply replyCount(Reply reply);

	List<Reply> replyMain(Reply reply);


}
