package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDaoImpl implements ReplyDao {
	private final SqlSession session;
	
	@Override
	public List<Reply> getReplyByArticle(Article article) {
		return session.selectList("hgGetReplyByArticle", article);
	}
}
