package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	int 			totalArticle(Article article);

	List<Article> 	listArticle(Article article);
	Article 		detailArticle(Article article);

	List<Region> 	regionName();
	List<Region>	parentRegionName();
	List<Comm> 		categoryName();
	List<Comm>		genderName();

//	int 			favoriteCount(Article a);

	List<Article> 	replyList(Article article);

	void 			writeArticle(Article article);
	void			updateArticle(Article article);
	int 			deleteArticle(Article article);
	
	public 			int insertArticle(Article article);
	public 			Article getArticleById(Article searcher);

//	public 			int insertReply(Article article);
}
