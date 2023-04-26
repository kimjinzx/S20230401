package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {
	// 마이바티스 db연동
	private final SqlSession session;
	
	
	@Override
	public int totalArticle() {
		int totArticleCount = 0;
		System.out.println("ArticleImpl Start total...");
		
		try {
			totArticleCount = session.selectOne("ArticleTotal");
			System.out.println("ArticleImpl totalArticle totArticleCount->" + totArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleImpl totalArticle Exception->"+e.getMessage());
		}
		
		return totArticleCount;
	}

	@Override
	public List<Article> listArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleDaoImpl listArticle Start...");
		try {
			articleList = session.selectList("cyArticleListAll", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl listArticle e.getMessage()->"+e.getMessage());
		}
		return articleList;
	}

	@Override
	public Article cyArticlereadDetail(Article article) {
		System.out.println("ArticleDaoImpl article Start...");
		Article result = null;
		try {
			result = session.selectOne("cyArticlereadDetail", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}

	@Override
	public Article detatilArticle(int art_title) {
		System.out.println("ArticleDaoImpl detail start..");
		Article article = new Article();
		
		try {
			article = session.selectOne("cyArticleSelOne", art_title);
			System.out.println("ArticleImpl detail brd_id->" +article.getBrd_id());
		} catch (Exception e) {
			System.out.println("articleDaoImpl detail Excpetion->"+e.getMessage());
		}
		
		return article;
	}

	@Override
	public Article cyArticlereadupdate(Article article) {
		System.out.println("ArticleDaoImpl article Start...");
		Article result = null;
		try {
			result = session.selectOne("cyArticlereadupdate", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	// 게시물 작성
	@Override
	public int insert(Article article) {
		System.out.println("ArticleDaoImpl insert Start...");
		int result = 0;
		try {
			result = session.insert("insert", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	//게시물 수정
	@Override
	public int modify(Article article) {
		System.out.println(article);
		System.out.println("ArticleDaoImpl update Start...");
		int result = 0;
		try {
			result = session.update("update", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}

}
