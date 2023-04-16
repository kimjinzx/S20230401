package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {

	// Mybatis DB 연동 
	private final SqlSession  session;
	
	@Override
	public int totalArticle() {
		int totArticleCount = 0;
		System.out.println("ArticleDaoImpl Start total...");
		
		try {
			totArticleCount = session.selectOne("articleTotal");
			System.out.println("ArticleDaoImpl totalArticle totArticleCount->" +totArticleCount);
			
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception->"+e.getMessage());
		}
		return totArticleCount;
	}

	@Override
	public List<Article> listArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleDaoImpl listArticle Start ..." );
		try {
			//                             Map ID        parameter
			articleList = session.selectList("shArticleListAll", article);
			System.out.println("ArticleDaoImpl listArticle articleList.size()->"+articleList.size());
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl listArticle e.getMessage()->"+e.getMessage());
		}
		return articleList;	
	}
	
}
