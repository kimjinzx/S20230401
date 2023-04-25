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
	public int totalCustomer() {
		int totCustomerCount = 0;
		System.out.println("ArticleDaoImpl Start totalCustomer...");
		
		try {
			totCustomerCount = session.selectOne("shCustomerCount");
			System.out.println("ArticleDaoImpl shCustomerCount totCustomerCount->" +totCustomerCount);
			
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl shCustomerCount Exception->"+e.getMessage());
		}
		return totCustomerCount;
	}
	
	@Override
	public List<Article> listCustomer(Article article) {
		List<Article> customerList = null;
		System.out.println("ArticleDaoImpl listCustomer Start ..." );
		try {
			//                             Map ID        parameter
			customerList = session.selectList("shListCustomer", article);
			System.out.println("ArticleDaoImpl listCustomer customerList.size()->"+customerList.size());
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl listCustomer e.getMessage()->"+e.getMessage());
		}
		return customerList;	
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

	@Override
	public List<Article> listManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> listCustomerMenu(Article article) {
		List<Article> listMenu = null;
		try {
			listMenu = session.selectList("shListCustomerMenu", article);
			System.out.println("다오 리스트메뉴"+listMenu);
		} catch (Exception e) {
			System.out.println("메뉴에러"+e.getMessage());
		}
			
		return listMenu;
	}


}
