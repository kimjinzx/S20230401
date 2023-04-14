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
		int totalArticleCnt = ad.totalArticle();
		return totalArticleCnt;
	}

	@Override
	public List<Article> articleTotal(Article article) {
		List<Article> articleList = null;
		articleList = ad.articleTotal(article);
		return articleList;
	}

}
