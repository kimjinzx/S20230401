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
	public int totalArticle() {
		int totArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");
		
		try {
			totArticleCount = session.selectOne("ArticleTotal");
			// selectOne -> 한개의 row 호출 (Article.xml의 ArticleTotal 안의 sql문 실행)
			// resultType의 type으로 (여기선 int)로 반환해줌.
			// totArticleCount에 해당 값이 (여기선 '19') 담기게 됨.
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
			//										Map ID		parameter
			articleList = session.selectList("tkArticleListAll", article);
			// selectList => 여러 개의 List를 호출 (List형태로 반환)
			// 여러개의 parameter를 넣으려면 parameter에 DTO를 넣어라?
			System.out.println("ArticleDaoImpl listArticle ArticleList.size()-> " + articleList.size());
		} catch(Exception e) {
			System.out.println("ArticleDaoImpl listArticle e.getMessage()-> " + e.getMessage());
		}
		return articleList;
	}
	
	@Override
	public int totalEatingArticle() {
		int totEatingArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");
		
		try {
			totEatingArticleCount = session.selectOne("ArticleEatingTotal");
			// selectOne -> 한개의 row 호출 (Article.xml의 ArticleTotal 안의 sql문 실행)
			// resultType의 type으로 (여기선 int)로 반환해줌.
			// totArticleCount에 해당 값이 (여기선 '19') 담기게 됨.
			System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totEatingArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
		}
		return totEatingArticleCount;
	}


	@Override
	public int totalSportsArticle() {
		int totSportsArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");
		
		try {
			totSportsArticleCount = session.selectOne("ArticleSportsTotal");
			// selectOne -> 한개의 row 호출 (Article.xml의 ArticleTotal 안의 sql문 실행)
			// resultType의 type으로 (여기선 int)로 반환해줌.
			// totArticleCount에 해당 값이 (여기선 '19') 담기게 됨.
			System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totSportsArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
		}
		return totSportsArticleCount;
	}

	@Override
	public int totalShoppingArticle() {
			int totShoppingArticleCount = 0;
			System.out.println("ArticleDaoImple Start total...");
			
			try {
				totShoppingArticleCount = session.selectOne("ArticleShoppingTotal");
				// selectOne -> 한개의 row 호출 (Article.xml의 ArticleTotal 안의 sql문 실행)
				// resultType의 type으로 (여기선 int)로 반환해줌.
				// totArticleCount에 해당 값이 (여기선 '19') 담기게 됨.
				System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totShoppingArticleCount);
			} catch (Exception e) {
				System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
			}
			return totShoppingArticleCount;
	}

	@Override
	public int totalCurtureArticle() {
		int totCurtureArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");
		
		try {
			totCurtureArticleCount = session.selectOne("ArticleCurtureTotal");
			// selectOne -> 한개의 row 호출 (Article.xml의 ArticleTotal 안의 sql문 실행)
			// resultType의 type으로 (여기선 int)로 반환해줌.
			// totArticleCount에 해당 값이 (여기선 '19') 담기게 됨.
			System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totCurtureArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
		}
		return totCurtureArticleCount;
	}

	@Override
	public int totalHobbyArticle() {
		int totHobbyArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");
		
		try {
			totHobbyArticleCount = session.selectOne("ArticleHobbyTotal");
			// selectOne -> 한개의 row 호출 (Article.xml의 ArticleTotal 안의 sql문 실행)
			// resultType의 type으로 (여기선 int)로 반환해줌.
			// totArticleCount에 해당 값이 (여기선 '19') 담기게 됨.
			System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totHobbyArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
		}
		return totHobbyArticleCount;
	}

	@Override
	public int totalEtcArticle() {
		int totEtcArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");
		
		try {
			totEtcArticleCount = session.selectOne("ArticleEtcTotal");
			// selectOne -> 한개의 row 호출 (Article.xml의 ArticleTotal 안의 sql문 실행)
			// resultType의 type으로 (여기선 int)로 반환해줌.
			// totArticleCount에 해당 값이 (여기선 '19') 담기게 됨.
			System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totEtcArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
		}
		return totEtcArticleCount;
	}
	
	@Override
	public List<Article> listEatingArticle(Article article) {
		List<Article> articleEatingList = null;
		System.out.println("ArticleDaoImpl listEatingArticle Start...");
		try {
			//										Map ID		parameter
			articleEatingList = session.selectList("tkArticleEatingListAll", article);
			// selectList => 여러 개의 List를 호출 (List형태로 반환)
			// 여러개의 parameter를 넣으려면 parameter에 DTO를 넣어라?
			System.out.println("ArticleDaoImpl listEatingArticle ArticleList.size()-> " + articleEatingList.size());
		} catch(Exception e) {
			System.out.println("ArticleDaoImpl listEatingArticle e.getMessage()-> " + e.getMessage());
		}
		return articleEatingList;
	}

	@Override
	public List<Article> listSportsArticle(Article article) {
		List<Article> articleSportsList = null;
		System.out.println("ArticleDaoImpl listSportsArticle Start...");
		try {
			//										Map ID		parameter
			articleSportsList = session.selectList("tkArticleSportsListAll", article);
			// selectList => 여러 개의 List를 호출 (List형태로 반환)
			// 여러개의 parameter를 넣으려면 parameter에 DTO를 넣어라?
			System.out.println("ArticleDaoImpl listSportsArticle ArticleList.size()-> " + articleSportsList.size());
		} catch(Exception e) {
			System.out.println("ArticleDaoImpl listSportsArticle e.getMessage()-> " + e.getMessage());
		}
		return articleSportsList;
	}

	@Override
	public List<Article> listShoppingArticle(Article article) {
		List<Article> articleShoppingList = null;
		System.out.println("ArticleDaoImpl listShoppingArticle Start...");
		try {
			//										Map ID		parameter
			articleShoppingList = session.selectList("tkArticleShoppingListAll", article);
			// selectList => 여러 개의 List를 호출 (List형태로 반환)
			// 여러개의 parameter를 넣으려면 parameter에 DTO를 넣어라?
			System.out.println("ArticleDaoImpl listShoppingArticle ArticleList.size()-> " + articleShoppingList.size());
		} catch(Exception e) {
			System.out.println("ArticleDaoImpl listShoppingArticle e.getMessage()-> " + e.getMessage());
		}
		return articleShoppingList;
	}

	@Override
	public List<Article> listCurtureArticle(Article article) {
		List<Article> articleCurtureList = null;
		System.out.println("ArticleDaoImpl listCurtureArticle Start...");
		try {
			//										Map ID		parameter
			articleCurtureList = session.selectList("tkArticleCurtureListAll", article);
			// selectList => 여러 개의 List를 호출 (List형태로 반환)
			// 여러개의 parameter를 넣으려면 parameter에 DTO를 넣어라?
			System.out.println("ArticleDaoImpl listCurtureArticle ArticleList.size()-> " + articleCurtureList.size());
		} catch(Exception e) {
			System.out.println("ArticleDaoImpl listCurtureArticle e.getMessage()-> " + e.getMessage());
		}
		return articleCurtureList;
	}

	@Override
	public List<Article> listHobbyArticle(Article article) {
		List<Article> articleHobbyList = null;
		System.out.println("ArticleDaoImpl listHobbyArticle Start...");
		try {
			//										Map ID		parameter
			articleHobbyList = session.selectList("tkArticleHobbyListAll", article);
			// selectList => 여러 개의 List를 호출 (List형태로 반환)
			// 여러개의 parameter를 넣으려면 parameter에 DTO를 넣어라?
			System.out.println("ArticleDaoImpl listHobbyArticle ArticleList.size()-> " + articleHobbyList.size());
		} catch(Exception e) {
			System.out.println("ArticleDaoImpl listHobbyArticle e.getMessage()-> " + e.getMessage());
		}
		return articleHobbyList;
	}

	@Override
	public List<Article> listEtcArticle(Article article) {
		List<Article> articleEtcList = null;
		System.out.println("ArticleDaoImpl listEtcArticle Start...");
		try {
			//										Map ID		parameter
			articleEtcList = session.selectList("tkArticleEtcListAll", article);
			// selectList => 여러 개의 List를 호출 (List형태로 반환)
			// 여러개의 parameter를 넣으려면 parameter에 DTO를 넣어라?
			System.out.println("ArticleDaoImpl listEtcArticle ArticleList.size()-> " + articleEtcList.size());
		} catch(Exception e) {
			System.out.println("ArticleDaoImpl listEtcArticle e.getMessage()-> " + e.getMessage());
		}
		return articleEtcList;
	}

}
