package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleDaoImple implements ArticleDao {
	private final SqlSession session;
	
	@Override
	public int allTotalArt(Article article) {
		int allArtCnt = 0;
		try {
			allArtCnt = session.selectOne("dgAllArtCnt", article);
			log.info("Article 전체 게시판 카운트 : {}" ,allArtCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allArtCnt;
	}
	
	@Override
	public List<Article> allArticleList(Article article) {
		List<Article> allArticleList = null;
		try {
			allArticleList = session.selectList("dgAllArticleList", article);
			log.info("Article 전체 게시판 조회 : {}", allArticleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allArticleList;
	}
	
	@Override
	public int totalArt(Article article) {
		int artCnt = 0;
		try {
			artCnt = session.selectOne("dgArtCnt", article);
			log.info("Article 게시판 카운트 : {}", artCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artCnt;
	}

	@Override
	public List<Article> articleList(Article article) {
		List<Article> articleList = null;
		try {
			articleList = session.selectList("dgArticleList", article);
			log.info("Article {} 게시판 조회", articleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}

	// 게시글 조회 (art_id, brd_id)
	@Override
	public Article detailArticle(Article article) {
		Article detailArticle = null;
		try {
			detailArticle = session.selectOne("dgDetailArticle",article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailArticle;
	}
	
	// 게시글 조회수 증가
	@Override
	public int readPlusArticle(Article article) {
		int result = 0;
		try {
			result = session.update("dgReadPlusArticle", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
