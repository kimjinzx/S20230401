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
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao ad;
	
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

	@Override
	public int replyWrite(Reply reply) {
		int reWrite = 0;
		reWrite = ad.replyWrite(reply);
		return reWrite;
	}

	@Override
	public int replyDelete(Reply reply) {
		int reDelete = 0;
		reDelete = ad.replyDelete(reply);
		return reDelete;
	}
}
