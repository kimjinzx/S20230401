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
	// 총리스트 	
	@Override
	public int totalArticle() {
		System.out.println("ArticleServiceImpl Start total...");
		int totArticleCnt = ad.totalArticle();
		System.out.println("ArticleServiceImpl totalArticle totArticleCnt->" + totArticleCnt);
		return totArticleCnt;
	}
	
	//리스트 조회
	@Override
	public List<Article> listArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleServiceImpl listManager Start..");
		articleList = ad.listArticle(article);
		System.out.println("ArticleServiceImpl listArticle articleList.size()->" +articleList.size());
		return articleList;
	}
	
	// 상세페이지 조회
	@Override
	public Article cyArticlereadDetail(Article article) {
		System.out.println("ArticleServiceImpl Manager Start..");
		Article result = ad.cyArticlereadDetail(article);
		System.out.println("ArticleServiceImpl cyArticlereadDetail article->" +article);
		return result;
	}
	// 상세페이지?
	@Override
	public Article detailArticle(int art_title) {
		System.out.println("ArticleServiceImpl detail...");
		Article article = null;
		article = ad.detatilArticle(art_title);
		return article;
	}
	
	// 수정페이지 상세페이지
	@Override
	public Article cyArticlereadupdate(Article article) {
		System.out.println("ArticleServiceImpl Manager Start..");
		Article result = ad.cyArticlereadupdate(article);
		System.out.println("ArticleServiceImpl cyArticlereadDetail article->" +article);
		return result;
	}
	
	// 게시물 수정
	@Override
	public int cyArticlemodify(Article article) {
		System.out.println("ArticleServiceImpl update");
		int result = ad.cyArticlemodify(article);
		return result;
	}
	
	
	// 게시물 작성
	@Override
	public int cyArticleinsert(Article article) {
		System.out.println("ArticleServiceImpl insert...");
		int result = ad.cyArticleinsert(article);
		return result;
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
