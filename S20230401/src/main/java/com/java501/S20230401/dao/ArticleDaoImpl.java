package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {
	// 마이바티스 db연동
	private final SqlSession session;
	
	// 총리스트
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
	// 리스트조회
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
	//상세페이지
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
	// 상세페이지
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
	//상세페이지 수정
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
	public int cyArticleinsert(Article article) {
		System.out.println("ArticleDaoImpl insert Start...");
		int result = 0;
		try {
			result = session.insert("cyArticleinsert", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	//게시물 수정
	@Override
	public int cyArticlemodify(Article article) {
		System.out.println(article);
		System.out.println("ArticleDaoImpl modify Start...");
		int result = 0;
		try {
			result = session.update("cyArticlemodify", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	
	
	@Override
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType) {
		List<ArticleMember> articleList = null;
		articleList = session.selectList("hgGetSummary" + summaryType.toString(), boardNum);
		for (ArticleMember am : articleList) {
			try { am.setHis_good(session.selectOne("hgCalculateGood", am)); }
			catch(Exception e) { am.setHis_good(0); }
			try { am.setHis_normal(session.selectOne("hgCalculateNormal", am)); }
			catch(Exception e) { am.setHis_normal(0); }
			try { am.setHis_bad(session.selectOne("hgCalculateBad", am)); }
			catch(Exception e) { am.setHis_bad(0); }
			if (am.getTrd_id() != 0) {
				try {
					am.setTrd_enddate(session.selectOne("hgCalculateEnddate", am));
				} catch(Exception e) {
					
				}
			}
			try { am.setRep_count(session.selectOne("hgCountReply", am)); }
			catch(Exception e) { am.setRep_count(0); }
		}
		return articleList;
	}
	
	@Override
	public int insertArticle(Article article) {
		return session.insert("hgInsertArticle", article);
	}
	
	@Override
	public Article getArticleById(Article searcher) {
		return session.selectOne("hgGetArticleById", searcher);
	}
}
