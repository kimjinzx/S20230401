package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WaitingDaoImpl implements WaitingDao {
	private final SqlSession session;
	
	
	@Override
	public int jhWaitListYN(Article article) {
		return session.selectOne("JHWaitListYN", article);

	}

}
