package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Trade;

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
	@Override
	public void shareTradeStatus(Article article) {
		int result = 0;
		try {
			Trade trade = session.selectOne("dgGetTrade", article);
			// 최대인원 확인
			result = session.selectOne("dgShareTradeMax", trade);
			if(result > 0) {
				trade.setTrd_status(402);
				result = session.update("dgShareTradeStatus",trade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
