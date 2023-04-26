package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;
import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao ad;
	
	@Override
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType) {
		List<ArticleMember> articleList = ad.getArticleSummary(boardNum, summaryType);
		return articleList;
	}
	
	@Override
	public int insertArticle(Article article) {
		int result = ad.insertArticle(article);
		return result;
	}
	
	@Override
	public Article getArticleById(Article searcher) {
		return ad.getArticleById(searcher);
	}
	
	
	
	
	
	@Override//진현
	public List<Article_Trade_Reply> getDutchpayList(String boardName) {
		List<Article_Trade_Reply> atr = ad.getDutchpayList(boardName);
		return atr;
	}

	@Override
	public Article_Trade_Reply detail1(Article_Trade_Reply atr) {
		Article_Trade_Reply atr1 = null;
		atr1 = ad.detail2(atr);
		return atr1;
	}

	@Override
	public List<Comm> category1() {
		List<Comm> cm = null;
		cm = ad.category2();
		return cm;
	}

	@Override
	public List<Region> loc1() {
		List<Region> re = null;
		re = ad.loc2();
		return re;
	}

	@Override
	public void dutchpayInsert1(Article_Trade_Reply atr) {
		ad.dutchpayInsert2(atr);
	}
	
	@Override
	public Article_Trade_Reply updateForm1(Article_Trade_Reply atr) {
		Article_Trade_Reply updateForm = null;
		updateForm = ad.updateForm2(atr);
		return updateForm;
	}

	@Override
	public List<Region> loc_ud1() {
		List<Region> re = null;
		re = ad.loc_ud2();
		return re;
	}

	@Override
	public void dutchpayUpdate1(Article_Trade_Reply atr) {
		ad.dutchpayUpdate2(atr);
		
	}

	@Override
	public void dutchpayDelete1(Article_Trade_Reply atr) {
		ad.dutchpayDelete2(atr);
		
	}



	}//진현







	



