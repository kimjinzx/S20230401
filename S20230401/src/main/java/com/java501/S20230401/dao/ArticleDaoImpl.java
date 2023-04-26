package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;
import oracle.security.o3logon.a;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {
	private final SqlSession session;
	
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

	@Override
	public void writeArticle(Article article) {
		System.out.println("ArticleDaoImpl wirteArticle start...");
		try {
			if (article.getReg_id2() == null) {
				article.setReg_id(article.getReg_id1());
			} else {
				article.setReg_id(article.getReg_id2());
			}
			session.selectOne("insertArticle", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl insertArticle => " + e.getMessage());
		}
	}

	@Override
	public int deleteArticle(Article article) {
		int deleteArticle = 0;

		try {
			deleteArticle = session.update("deleteArticle", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl deleteArticle => " + e.getMessage());
		}
		return deleteArticle;
	}

	@Override
	public void updateArticle(Article article) {
		System.out.println("ArticleDaoImpl updateArticle start...");
		try {
			if (article.getReg_id2() == null) {
				article.setReg_id(article.getReg_id1());
			} else {
				article.setReg_id(article.getReg_id2());
			}
			session.selectOne("updateArticle", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl updateArticle => " + e.getMessage());
		}
	}
}

//	@Override
//	@Transactional
//	public int updateArticle(Article article) {
//		int updateArticleCnt = 0;
//		int updateTradeCnt = 0;
//		int updateArticle = 0;
//
//		System.out.println("updateArticleDaoImpl Start...");
//		System.out.println("updateArticleDaoImpl article->"+article);
//		
//		try {
//			updateArticleCnt = session.update("updateTrade", article);  
//			updateTradeCnt 	 = session.update("updateArticle", article);
//			System.out.println("updateArticleDaoImpl updateArticleCnt->"+updateArticleCnt);
//			System.out.println("updateArticleDaoImpl updateTradeCnt->"+updateTradeCnt);
//			if (updateArticleCnt > 0 && updateTradeCnt > 0)	updateArticle = 1;
//			else updateArticle = 0;
//			System.out.println("updateArticleDaoImpl updateArticle->"+updateArticle);
//				
//		} catch (Exception e) {
//			System.out.println("ArticleDaoImpl updateArticle => " + e.getMessage());
//			updateArticle = 0;
//		}
//		return updateArticle;
//	}