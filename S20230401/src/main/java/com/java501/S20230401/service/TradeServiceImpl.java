package com.java501.S20230401.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.dao.TradeDao;
import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
	private final TradeDao td;
	private final ArticleDao ad;
	
	// 양동균
	// 거래글 수정
	@Override
	@Transactional(value = "transactionManager", rollbackFor = {RuntimeException.class, Exception.class})
	public int updateShare(Article article) {
		int result = 0;
		try {
			result = td.updateShare(article);
			result = ad.updateShare(article);
		} catch (Exception e) {
			// 예외 발생시 롤백
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}
	// 거래 상태 변경
	@Override
	public void shareTradeStatus(Article article) { td.shareTradeStatus(article); }

}
