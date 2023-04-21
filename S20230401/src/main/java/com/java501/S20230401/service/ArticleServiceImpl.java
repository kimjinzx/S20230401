package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.dao.CommDao;
import com.java501.S20230401.dao.RegionDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleDao ad;
	private final RegionDao rd;
	private final CommDao cd;

	@Override
	public int totalArticle(Article article) {
		System.out.println("ArticleService Start total...");
		int totArticleCnt = ad.totalArticle(article);
		System.out.println("ArticleServiceImpl totalArticle totArticleCnt-> " + totArticleCnt);

		return totArticleCnt;
	}
	

	@Override
	public List<Article> listArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleServiceImpl listManager Start...");
		articleList = ad.listArticle(article);
		System.out.println("ArticleServiceImpl listArticle ArticleList.size()->" + articleList.size());
		return articleList;
	}


	@Override
	public List<Article> detailArticle(Article article) {
		List<Article> detailArticle = null;
		detailArticle = ad.detailArticle(article);
		return detailArticle;
	}


	@Override
	public List<Region> regionName() {
		List<Region> regionName = null;
		System.out.println("ArticleServiceImpl regionName start...");
		regionName = rd.regionName();
		System.out.println("ArticleServiceImpl regionName.size()=> " + regionName.size());
		
		return regionName;
	}


	@Override
	public List<Comm> categoryName() {
		List<Comm> commName = null;
		System.out.println("ArticleServiceImpl categoryName start...");
		commName = cd.commName();
		System.out.println("ArticleServiceImpl commName.size()=> " + commName.size());
		return commName;
	}


	@Override
	public int favoriteCount(Article a) {
		int favoriteCount = ad.favoriteCount(a);
		return favoriteCount;
	}

	@Override
	public List<Article> replyList(Article article) {
		List<Article> replyList = ad.replyList(article);
		return replyList;
	}

//	@Override
//	public int insertTrade(Article article) {
//		
//		int insertTrade = ad.insertTrade(article);
//		System.out.println("ArticleServiceImpl insertTrade.size()-> " + insertTrade);
//		return insertTrade;
//	}
	@Override
	public int writeArticle(Article article) {
		
		int writeArticle = ad.writeArticle(article);
		System.out.println("ArticleServiceImpl insertArticle.size()-> " + writeArticle);
		return writeArticle;
	}


	@Override
	public int deleteArticle(Article article) {
		
		int deleteArticle = ad.deleteArticle(article);
		System.out.println("ArticleServiceImpl deleteArticle.size()=> " + deleteArticle);
		return deleteArticle;
	}






}
