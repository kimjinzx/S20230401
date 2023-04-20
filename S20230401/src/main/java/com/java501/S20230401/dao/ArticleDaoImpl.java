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
}
