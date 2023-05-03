package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Trade;
import com.java501.S20230401.model.TradeInfo;
import com.java501.S20230401.util.TradeType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TradeDaoImpl implements TradeDao {
	private final SqlSession session;
	
	@Override
	public List<TradeInfo> hgGetTradesByType(Trade searcher, TradeType type) {
		return session.selectList("hgGetRecent" + type.toString() + "Trade", searcher);
	}
}
