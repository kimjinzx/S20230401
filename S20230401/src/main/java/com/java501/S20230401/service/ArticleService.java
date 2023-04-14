package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	public List<Article> getArticleSummary(int boardNum, SummaryType summaryType);
}
