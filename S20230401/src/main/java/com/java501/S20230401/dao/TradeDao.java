package com.java501.S20230401.dao;

import com.java501.S20230401.model.Article;

public interface TradeDao {

	// 양동균
	int updateShare(Article article); // 거래글 수정
	void shareTradeStatus(Article article); // 거래 상태 수정
}
