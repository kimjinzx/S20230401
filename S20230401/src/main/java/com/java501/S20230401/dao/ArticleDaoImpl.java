package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao {
	
	private final SqlSession session;
	
	@Override
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
			System.out.println("ArticleServiceImpl detail2 Exception -> "+e.getMessage());
		}
		return atr2;
	}

	@Override
	public void dutchpayInsert2(Article_Trade_Reply atr) {
		try {
			System.out.println(atr);
			session.selectOne("JHInsert",atr);
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl dutchpayInsert2 Exception -> "+e.getMessage());
		}
	}
	
	@Override
	public List<Comm> category2() {
		List<Comm> cm = null;
		try {
			cm = session.selectList("JHCategory");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl category2 Exception -> "+e.getMessage());
		}
		return cm;
	}


	@Override
	public List<Region> loc2() {
		List<Region> re = null;
		try {
			re = session.selectList("JHLoc");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl region2 Exception -> "+e.getMessage());
		}
		return re;
	}

	
	@Override
	public Article_Trade_Reply updateForm2(Article_Trade_Reply atr) {
		Article_Trade_Reply updateForm = null;
		try {
			updateForm = session.selectOne("JHUpdateForm",atr);
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl updateForm2 Exception -> "+e.getMessage());
		}
		return updateForm;
	}

	@Override
	public List<Comm> category_ud2() {
		List<Comm> cm = null;
		try {
			cm = session.selectList("JHCategoryUd");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl category_ud2 Exception -> "+e.getMessage());
		}
		return cm;
	}

	@Override
	public List<Region> loc_ud2() {
		List<Region> re = null;
		try {
			re = session.selectList("JHLocUd");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl loc_ud2 Exception -> "+e.getMessage());
		}
		return re;
	}










	
}
