package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao	ad;

	@Override
	public Integer totalCustomer() {
		System.out.println("ArticleServiceImpl Start totalCustomer..." );
		int totCustomerCnt = ad.totalCustomer();
		System.out.println("ArticleServiceImpl totalCustomer totCustomerCnt->" + totCustomerCnt);
		return totCustomerCnt;
	}
	
	@Override
	public List<Article> listCustomer(Article article) {
		List<Article> customerList = null;
		System.out.println("ArticleServiceImpl listCustomer Start..." );
		customerList = ad.listCustomer(article);
		System.out.println("ArticleServiceImpl list customerList.size()->" +customerList.size());
		return customerList;
	}
	
	@Override
	public Integer totalNotice() {
		System.out.println("ArticleServiceImpl Start totalNotice..." );
		int totNoticeCnt = ad.totalNotice();
		System.out.println("ArticleServiceImpl totalNotice totNoticeCnt->" + totNoticeCnt);
		return totNoticeCnt;
	}

	@Override
	public Article detailCustomer(Article article) {
		System.out.println("ArticleServiceImpl detailCustomer...");
		Article customerDetail = null;
		customerDetail = ad.detailCustomer(article);
		return customerDetail;
	}


}
