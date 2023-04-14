package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.util.SummaryType;

@Repository
public interface ArticleDao {

	List<Article> getArticleSummary(int boardNum, SummaryType summaryType);

}
