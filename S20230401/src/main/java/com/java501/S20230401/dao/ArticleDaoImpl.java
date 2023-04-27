package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;


import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {
	
	private final SqlSession session;
	
	@Override//진현
	public List<Article_Trade_Reply> getDutchpayList(String boardName) {
		List<Article_Trade_Reply> dutchpayList2 = null;
		
		try {
			dutchpayList2 = session.selectList("JHDutchpay" + boardName);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl dutchpayList2 Exception -> "+e.getMessage());
		}
		return dutchpayList2;
	}
	
	@Override
	public Article_Trade_Reply detail2(Article_Trade_Reply atr) {
		Article_Trade_Reply atr2 = null;
		try {				
			atr2 = session.selectOne("JHDutchpayDetail",atr);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl detail2 Exception -> "+e.getMessage());
		}
		return atr2;
	}

	@Override
	public void dutchpayInsert2(Article_Trade_Reply atr) {
		try {
			System.out.println(atr);
			session.selectOne("JHInsert",atr);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl dutchpayInsert2 Exception -> "+e.getMessage());
		}
	}
	
	@Override
	public List<Comm> category2() {
		List<Comm> cm = null;
		try {
			cm = session.selectList("JHCategory");
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl category2 Exception -> "+e.getMessage());
		}
		return cm;
	}


	@Override
	public List<Region> loc2() {
		List<Region> re = null;
		try {
			re = session.selectList("JHLoc");
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl region2 Exception -> "+e.getMessage());
		}
		return re;
	}

	
	@Override
	public Article_Trade_Reply updateForm2(Article_Trade_Reply atr) {
		Article_Trade_Reply updateForm = null;
		try {
			updateForm = session.selectOne("JHUpdateForm",atr);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl updateForm2 Exception -> "+e.getMessage());
		}
		return updateForm;
	}

	@Override
	public List<Region> loc_ud2() {
		List<Region> re = null;
		try {
			re = session.selectList("JHLocUd");
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl loc_ud2 Exception -> "+e.getMessage());
		}
		return re;
	}
	
	@Override
	public void dutchpayUpdate2(Article_Trade_Reply atr) {
		try {
			System.out.println(atr);
			session.selectOne("JHUpdate",atr);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl dutchpayUpdate2 Exception -> "+e.getMessage());
		}
	}
	
	@Override
	public void dutchpayDelete2(Article_Trade_Reply atr) {
		try {
			session.selectOne("JHDelete",atr);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl dutchpayDelete2 Exception -> "+e.getMessage());
		}
	}
	//진현
	
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
