package com.java501.S20230401.dao;

import java.util.List;


import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.MemberInfo;
import com.java501.S20230401.util.SummaryType;

public interface ArticleDao {

	// 유현규
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);
	public int insertArticle(Article article);
	public Article getArticleById(Article searcher);
	public MemberInfo getMemberInfoById(int mem_id);
	public ArticleMember getArticleMemberById(Article searcher);
	
	
	// 양동균
	int allTotalArt(Article article);
	List<Article> allArticleList(Article article);
	Article detailShareArticle(Article article);// 댓글
	int readShareArticle(Article article);
	int writeShareArticle(Article article);// 글쓰기

	
	// 백준
	Integer 						totalArticle(int brd_id);
	List<Article> 					articleTotal(Article article);
	Article 						detailContent(Article article);
	Integer							replyCount(int art_id);
	List<Article> 					artcleMenu(Article article);
	Integer 						upreadCount(Article article);
	List<Article> 					listMagnager();
	int 							bjWriteArticle(Article article);
	int 							bjUpdateArticle(Article article);
	int								delete(Article article);
	
	
	// 임동빈
	int 			totalArticle(Article article);
	List<Article> 	dbListArticle(Article article);
	Article 		detailArticle(Article article);
	List<Article> 	replyList(Article a);
	void 			dbWriteArticle(Article article);
	void 			dbUpdateArticle(Article article);
	int 			deleteArticle(Article article);
	
	
	// 김진현
	List<Article_Trade_Reply> 			getDutchpayList(String boardName);
	Article_Trade_Reply                 detail2(Article_Trade_Reply atr);
	void 								dutchpayInsert2(Article_Trade_Reply atr);
	List<Comm>           				category2();
	List<Region>    			        loc2();
	Article_Trade_Reply 				updateForm2(Article_Trade_Reply atr);
	List<Comm> 							category_ud2();
	List<Region> 						loc_ud2();
	
	
	// 김찬영
	int				totalArticle();								
	List<Article> 	listArticle(Article article);				
	List<Article> 	listReply(Article article);				
	Article			cyArticlereadDetail(Article article);		
	Article 		cyArticlereadupdate(Article article);		
	Article 		detatilArticle(int art_title);				
	int				cyArticleinsert(Article article);			
	int				cyArticlemodify(Article article);			
	public int 		cyArticledelete(Article article);			
	int				updateView(Article article);
	int				updateGood(Article article);
	int				updateBad(Article article);
	
	// 최승환
	int				totalCustomer();
	List<Article> 	listCustomer(Article article);
	Article 		detailCustomer(Article article);
	List<Article> 	listCustomerMenu(Article article);
	int 			insertCustomer(Article article);
	
	
}
