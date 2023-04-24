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
	public Integer totalCustomer() {
		int totCustomerCount = 0;
		System.out.println("ArticleDaoImpl Start totalCustomer...");
		
		try {
			totCustomerCount = session.selectOne("shArticleIndex");
			System.out.println("ArticleDaoImpl totalCustomer totCustomerCount->" +totCustomerCount);
			
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalCustomer Exception->"+e.getMessage());
		}
		return totCustomerCount;
	}
	
	@Override
	public List<Article> listCustomer(Article article) {
		List<Article> customerList = null;
		System.out.println("ArticleDaoImpl listCustomer Start ..." );
		try {
			//                             Map ID        parameter
			customerList = session.selectList("shArticleListCustomer", article);
			System.out.println("ArticleDaoImpl listCustomer articleList.size()->"+customerList.size());
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl listCustomer e.getMessage()->"+e.getMessage());
		}
		return customerList;	
	}
	
	@Override
	public Integer totalNotice() {
		int totNoticeCount = 0;
		System.out.println("ArticleDaoImpl Start totalNotice...");
		
		try {
			totNoticeCount = session.selectOne("noticeTotal");
			System.out.println("ArticleDaoImpl totalNotice totNoticeCount->" +totNoticeCount);
			
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalNotice Exception->"+e.getMessage());
		}
		return totNoticeCount;
	}

	@Override
	public Article detailCustomer(Article article) {
		System.out.println("ArticleDaoImpl detailCustomer start...");
		Article customerDetail = new Article();
		try {
			customerDetail = session.selectOne("shCustomerDetail", article);
		
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl detail Exception->"+e.getMessage());
		}
		return customerDetail;
	}


}
