package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.dao.CommDao;
import com.java501.S20230401.dao.RegionDao;
import com.java501.S20230401.dao.ReplyDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;
import oracle.security.o3logon.a;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao 	ad;
	private final RegionDao 	rd;
	private final CommDao 		cd;
	private final ReplyDao 		rpd;

	// 유현규 로그인 기능 추가
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


	// 양동균
	@Override
	public int allTotalArt(Article article) { return ad.allTotalArt(article); }
	@Override
	public List<Article> allArticleList(Article article) { return ad.allArticleList(article); }
	// 댓글
	@Override
	public Article detailShareArticle(Article article) { return ad.detailShareArticle(article); }
	@Override
	public int readShareArticle(Article article) {	return ad.readShareArticle(article); }
	// 글쓰기
	@Override
	public int writeShareArticle(Article article) {	return ad.writeShareArticle(article);}

	



	// 백준
	@Override
	public Integer totalArticle(int brd_id) {
		Integer totalArticleCnt = ad.totalArticle(brd_id);
		return totalArticleCnt;
	}
	@Override
	public List<Article> articleTotal(Article article) {
		List<Article> articleList = null;
		articleList = ad.articleTotal(article);
		return articleList;
	}
	@Override
	public Article detailContent(Article article) {
		Article detailCon = null;
		detailCon = ad.detailContent(article);
		return detailCon;
	}
	@Override
	public List<Article> articleMenu(Article article) {
		List<Article> articleMenu = null;
		articleMenu = ad.artcleMenu(article);
		return articleMenu;
	}
	@Override
	public Integer upreadCount(Article article) {
		return ad.upreadCount(article);
	}
	@Override
	public List<Article> listMagnager() {
		List<Article> bjwrite = null;
		bjwrite = ad.listMagnager();
		return bjwrite;
	}
	@Override
	public int bjWriteArticle(Article article) {
		int result = 0;
		result = ad.bjWriteArticle(article);
		return result;
	}
	@Override
	public int bjUpdateArticle(Article article) {
		int update = 0;
		update = ad.bjUpdateArticle(article);
		return update;
	}
	@Override
	public int delete(Article article) {
		int delResult = 0;
		delResult = ad.delete(article);
		return delResult;
	}
	
	
	
	
	
	// 임동빈
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
	public Article detailArticle(Article article) {
		Article detailArticle = null;
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
	public List<Region> parentRegionName() {
		List<Region> parentRegionName = null;
		parentRegionName = rd.parentRegionName();
		return parentRegionName;
	}
	@Override
	public List<Comm> categoryName() {
		List<Comm> categoryName = null;
		System.out.println("ArticleServiceImpl categoryName start...");
		categoryName = cd.boardName();
		System.out.println("ArticleServiceImpl commName.size()=> " + categoryName.size());
		return categoryName;
	}
	
	@Override
	public List<Comm> genderName() {
		List<Comm> genderName = null;
		System.out.println("ArticleServiceImpl categoryName start...");
		genderName = cd.genderName();
		System.out.println("ArticleServiceImpl commName.size()=> " + genderName.size());
		return genderName;
	}
	@Override
	public List<Article> replyList(Article article) {
		List<Article> replyList = ad.replyList(article);
		return replyList;
	}
	@Override
	public void dbWriteArticle(Article article) {
		ad.dbWriteArticle(article);
		System.out.println("ArticleServiceImpl article.getInsert_result() => " + article.getInsert_result());
	}
	@Override
	public int deleteArticle(Article article) {
		
		int deleteArticle = ad.deleteArticle(article);
		System.out.println("ArticleServiceImpl deleteArticle.size()=> " + deleteArticle);
		return deleteArticle;
	}
	@Override
	public void dbUpdateArticle(Article article) {
		ad.dbUpdateArticle(article);
		System.out.println("ArticleServiceImpl article.getInsert_result())=> " + article.getInsert_result());
	}
	
	
	
	
}
