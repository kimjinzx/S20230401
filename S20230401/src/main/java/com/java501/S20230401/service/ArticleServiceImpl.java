package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao articleDao;

	@Override
	public int allTotalArt(Article article) {
		return articleDao.allTotalArt(article);
	}

	@Override
	public List<Article> allArticleList(Article article) {
		return articleDao.allArticleList(article);
	}

	@Override
	public int totalArt(Article article) {
		return articleDao.totalArt(article);
	}

	@Override
	public List<Article> articleList(Article article) {
		return articleDao.articleList(article);
	}

	@Override
	public Article detailArticle(Article article) {
		return articleDao.detailArticle(article);
	}

}
