package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {
	private final SqlSession session;
	
	@Override
	public List<Article> getArticleSummary(int boardNum, SummaryType summaryType) {
		List<Article> articleList = null;
		articleList = session.selectList("hgGetSummary" + summaryType.toString(), boardNum);
		return articleList;
	}
}
