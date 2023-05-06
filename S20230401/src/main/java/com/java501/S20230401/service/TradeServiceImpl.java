package com.java501.S20230401.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = {Exception.class})
	public int updateShare(Article article) {
		int result = 0;
		try {
			System.out.println(article.getTrade());
			result = td.updateShare(article);
			System.out.println("거래"+result);
			result = ad.updateShare(article);
			System.out.println("아띠끌"+result);
		} catch (Exception e) {
			// 예외 발생시 롤백
			throw e;
		}
		return result;
	}

}
