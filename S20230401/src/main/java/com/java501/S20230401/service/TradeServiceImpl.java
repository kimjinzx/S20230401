package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.TradeDao;
import com.java501.S20230401.model.Trade;
import com.java501.S20230401.model.TradeInfo;
import com.java501.S20230401.util.TradeType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
	private final TradeDao td;
	
	@Override
	public List<TradeInfo> hgGetTradesByType(Trade searcher, TradeType type) {
		return td.hgGetTradesByType(searcher, type);
	}
}
