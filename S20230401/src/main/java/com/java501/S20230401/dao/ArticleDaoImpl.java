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
	public Integer totalNotice() {
		int totNoticeCount = 0;
		System.out.println("ArticleDaoImpl Start total...");
		
		try {
			totNoticeCount = session.selectOne("noticeTotal");
			System.out.println("ArticleDaoImpl totalNotice totNoticeCount->" +totNoticeCount);
			
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalNotice Exception->"+e.getMessage());
		}
		return totNoticeCount;
	}

	@Override
	public List<Article> listNotice(Article article) {
		List<Article> noticeList = null;
		System.out.println("ArticleDaoImpl listNotice Start ..." );
		try {
			//                             Map ID        parameter
			noticeList = session.selectList("shArticleListNotice", article);
			System.out.println("ArticleDaoImpl listNotice articleList.size()->"+noticeList.size());
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl listNotice e.getMessage()->"+e.getMessage());
		}
		return noticeList;	
	}

	@Override
	public Article detailNotice(Article article) {
		System.out.println("ArticleDaoImpl detail start...");
		Article noticeDetail = new Article();
		try {
			
			noticeDetail = session.selectOne("shNoticeSelOne", article);
		
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl detail Exception->"+e.getMessage());
		}
		return noticeDetail;
	}

}
