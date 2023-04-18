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
	public int totalArticle(Article article) {
		System.out.println("ArticleService Start total...");
		int totArticleCnt = ad.totalArticle(article);
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
	public List<Article> detailArticle(Article article) {
		List<Article> detailArticle = null;
		detailArticle = ad.detailArticle(article);
		return detailArticle;
	}


}
