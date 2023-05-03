package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Trade;
import com.java501.S20230401.model.TradeInfo;
import com.java501.S20230401.util.TradeType;

public interface TradeService {
	// 유현규
	public List<TradeInfo> hgGetTradesByType(Trade searcher, TradeType type);
}
