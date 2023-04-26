package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	public int insertArticle(Article article);

	public Article getArticleById(Article searcher);
	List<Article_Trade_Reply> 			getDutchpayList(String boardName);
	Article_Trade_Reply                 detail1(Article_Trade_Reply atr);
	List<Comm> 							category1();
	List<Region>         				loc1();
	void 								dutchpayInsert1(Article_Trade_Reply atr);
	Article_Trade_Reply		 			updateForm1(Article_Trade_Reply atr);
	List<Comm> 							category_ud1();
	List<Region> 						loc_ud1();
}