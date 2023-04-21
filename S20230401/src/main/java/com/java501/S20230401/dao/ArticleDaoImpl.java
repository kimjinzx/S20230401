package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {

	private final SqlSession session;

	@Override
	public int totalArticle(Article article) {
		int totArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");

		try {
			if (article.getBrd_id() == 1000) {
				totArticleCount = session.selectOne("ArticleTotalCnt");
			} else {
				totArticleCount = session.selectOne("ArticleBoardCnt", article);
			}
			System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
		}
		return totArticleCount;
	}

	@Override
	public List<Article> listArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleDaoImpl listArticle Start...");
		try {
			// article.getBrd_id() 따라서 분기 --> 전체
			if (article.getBrd_id() == 1000) {
				articleList = session.selectList("tkArticleListAll", article);
			} else {
				// 1010 밥/카페
				// 1020 스포츠/운동
				// 1030 쇼핑
				// 1040 문화 생활
				// 1050 취미 생활
				// 1060 기타

				articleList = session.selectList("tkArticleListBoard", article);
			}
			System.out.println("ArticleDaoImpl listArticle ArticleList.size()-> " + articleList.size());
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl listArticle e.getMessage()-> " + e.getMessage());
		}
		return articleList;
	}

	@Override
	public Article detailArticle(Article article) {
		Article detailArticle = null;
		System.out.println("ArticleDaoImpl detailArticle Start...");
		try {
			detailArticle = session.selectOne("tkArticleDetail", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl detailArticle e.getMessage()->" + e.getMessage());
		}
		return detailArticle;
	}

//	@Override
//	public int favoriteCount(Article a) {
//		Integer favoriteCount = 0;
//		try {
//			favoriteCount = session.selectOne("tkFavoriteCount", a);
//			if (favoriteCount == null) {
//				favoriteCount = 0;
//			}
//		} catch (Exception e) {
//			System.out.println("ArticleDaoImpl favoriteCount => " + e.getMessage());
//		}
//		return favoriteCount;
//	}
	

	@Override
	public List<Article> replyList(Article article) {
		List<Article> replyList = null;
		try {
			replyList = session.selectList("tkReplyList", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl replyList => " + e.getMessage());
		}
		return replyList;
	}
	
//	@Override
//	public int insertTrade(Article article) {
//		// TODO Auto-generated method stub
//		int insertTrade = 0;
//		try {
//			insertTrade = session.insert("insertTrade", article);
//		} catch (Exception e) {
//			System.out.println("ArticleDaoImpl insertTrade => " + e.getMessage());
//		}
//		return insertTrade;
//	}

	@Override
	public int writeArticle(Article article) {
		int insertTrade = 0;
		int insertArticle = 0;
		
		try {
			insertTrade = session.insert("insertTrade", article);
			if (insertTrade > 0) {
				insertArticle = session.insert("insertArticle", article);				
			}
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl insertArticle => " + e.getMessage());
		}
		return insertArticle;
	}

	@Override
	public int deleteArticle(Article article) {
		int deleteArticle = 0;
		int deleteTrade = 0;
		
		try {
			deleteArticle = session.delete("deleteArticle", article);				
			if (deleteArticle > 0) {
				deleteTrade = session.delete("deleteTrade", article);
			}
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl insertArticle => " + e.getMessage());
		}
		return deleteTrade;
	}
}
