package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TradeDaoImpl implements TradeDao {
	private final SqlSession session;
	
	@Override
	public int updateShare(Article article) {
		int result = 0;
		try {
			result = session.update("dgUpdateTrade", article.getTrade());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
