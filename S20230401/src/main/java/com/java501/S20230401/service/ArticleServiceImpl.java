package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;
import oracle.security.o3logon.a;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao ad;

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
	public int writeArticle(Article article) {
		int result = 0;
		result = ad.writeArticle(article);
		return result;
	}
	@Override
	public int updateArticle(Article article) {
		int update = 0;
		update = ad.updateArticle(article);
		return update;
	}
	@Override
	public int delete(Article article) {
		int delResult = 0;
		delResult = ad.delete(article);
		return delResult;
	}
	
}
