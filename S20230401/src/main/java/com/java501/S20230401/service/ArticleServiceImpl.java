package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao	ad;

	@Override
	public int totalCustomer() {
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
	public Article detailCustomer(Article article) {
		System.out.println("ArticleServiceImpl detailCustomer...");
		Article customerDetail = null;
		customerDetail = ad.detailCustomer(article);
		return customerDetail;
	}

	@Override
	public List<Article> listManager() {
		List<Article> articleList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleList = ad.listManager();
		System.out.println("ArticleServiceImpl listArticle articleList.size()->"+articleList.size());
		return articleList;
	}

	@Override
	public List<Article> listCustomerMenu(Article article) {
		List<Article> listMenu = null;
		listMenu = ad.listCustomerMenu(article);
		return listMenu;
	}
	
	@Override
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType) {
		List<ArticleMember> articleList = ad.getArticleSummary(boardNum, summaryType);
		return articleList;
	}
	
	@Override
	public int insertArticle(Article article) {
		int result = ad.insertArticle(article);
		return result;
	}
	
	@Override
	public Article getArticleById(Article searcher) {
		return ad.getArticleById(searcher);
	}
}
