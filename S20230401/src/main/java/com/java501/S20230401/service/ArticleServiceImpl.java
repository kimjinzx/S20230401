package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao ad;
	
	@Override
	public List<Article> getArticleSummary(int boardNum, SummaryType summaryType) {
		List<Article> articleList = ad.getArticleSummary(boardNum, summaryType);
		return articleList;
	}
}
