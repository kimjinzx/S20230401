package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;
import oracle.security.o3logon.a;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao ad;

	@Override
	public int allTotalArt(Article article) {return ad.allTotalArt(article);}
	@Override
	public List<Article> allArticleList(Article article) {return ad.allArticleList(article);}
	@Override
	public int totalArt(Article article) {return ad.totalArt(article);}
	@Override
	public List<Article> articleList(Article article) {return ad.articleList(article);}
	@Override
	public Article detailArticle(Article article) {	return ad.detailArticle(article);}
	@Override
	public int readPlusArticle(Article article) {	return ad.readPlusArticle(article);}

	
	// 로그인 기능 추가
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
