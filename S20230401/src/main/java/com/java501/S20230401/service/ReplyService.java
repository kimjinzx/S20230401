package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.ReplyMember;

public interface ReplyService {

	public List<ReplyMember> getReplyByArticle(Article article);

}
