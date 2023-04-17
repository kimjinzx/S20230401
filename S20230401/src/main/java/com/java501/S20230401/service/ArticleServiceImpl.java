package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleDao ad;

	@Override
	public int totalArticle() {
		System.out.println("ArticleService Start total...");
		int totArticleCnt = ad.totalArticle();
		System.out.println("ArticleServiceImpl totalArticle totArticleCnt-> " + totArticleCnt);
		
		return totArticleCnt;
	}
	

	@Override
	public List<Article> listArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleList = ad.listArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleList.size()->" + articleList.size());
		return articleList;
	}

	@Override
	public int totalEatingArticle() {
		System.out.println("ArticleService Start total...");
		int totEatingArticleCnt = ad.totalEatingArticle();
		System.out.println("ArticleServiceImpl totalArticle totEatingArticleCnt-> " + totEatingArticleCnt);
		
		return totEatingArticleCnt;
	}

	@Override
	public int totalSportsArticle() {
		System.out.println("ArticleService Start total...");
		int totSportsArticleCnt = ad.totalSportsArticle();
		System.out.println("ArticleServiceImpl totalArticle totSportsArticleCnt-> " + totSportsArticleCnt);
		
		return totSportsArticleCnt;
	}

	@Override
	public int totalShoppingArticle() {
		System.out.println("ArticleService Start total...");
		int totShoppingArticleCnt = ad.totalShoppingArticle();
		System.out.println("ArticleServiceImpl totalArticle totShoppingArticleCnt-> " + totShoppingArticleCnt);
		
		return totShoppingArticleCnt;
	}

	@Override
	public int totalCurtureArticle() {
		System.out.println("ArticleService Start total...");
		int totCurtureArticleCnt = ad.totalCurtureArticle();
		System.out.println("ArticleServiceImpl totalArticle totCurtureArticleCnt-> " + totCurtureArticleCnt);
		
		return totCurtureArticleCnt;
	}

	@Override
	public int totalHobbyArticle() {
		System.out.println("ArticleService Start total...");
		int totHobbyArticleCnt = ad.totalHobbyArticle();
		System.out.println("ArticleServiceImpl totalArticle totHobbyArticleCnt-> " + totHobbyArticleCnt);
		
		return totHobbyArticleCnt;
	}

	@Override
	public int totalEtcArticle() {
		System.out.println("ArticleService Start total...");
		int totEtcArticleCnt = ad.totalEtcArticle();
		System.out.println("ArticleServiceImpl totalArticle totEtcArticleCnt-> " + totEtcArticleCnt);
		
		return totEtcArticleCnt;
	}
	
	@Override
	public List<Article> listEatingArticle(Article article) {
		List<Article> articleEatingList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleEatingList = ad.listEatingArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleEatingList.size()->" + articleEatingList.size());
		return articleEatingList;
	}

	@Override
	public List<Article> listSportsArticle(Article article) {
		List<Article> articleSportsList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleSportsList = ad.listSportsArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleSportsList.size()->" + articleSportsList.size());
		return articleSportsList;
	}

	
	@Override
	public List<Article> listCurtureArticle(Article article) {
		List<Article> articleCurtureList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleCurtureList = ad.listCurtureArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleCurtureList.size()->" + articleCurtureList.size());
		return articleCurtureList;
	}

	@Override
	public List<Article> listHobbyArticle(Article article) {
		List<Article> articleHobbyList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleHobbyList = ad.listHobbyArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleHobbyList.size()->" + articleHobbyList.size());
		return articleHobbyList;
	}

	@Override
	public List<Article> listEtcArticle(Article article) {
		List<Article> articleEtcList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleEtcList = ad.listEtcArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleEtcList.size()->" + articleEtcList.size());
		return articleEtcList;
	}

	@Override
	public List<Article> listShoppingArticle(Article article) {
		List<Article> articleShoppingList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleShoppingList = ad.listShoppingArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleShoppingList.size()->" + articleShoppingList.size());
		return articleShoppingList;
	}

}
