package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.model.MemberInfo;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	// 유현규 로그인
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);
	public int insertArticle(Article article);
	public Article getArticleById(Article searcher);
	public MemberInfo getMemberInfoById(int mem_id);
	public ArticleMember getArticleMemberById(Article searcher);

	// 양동균
	int allTotalArt(Article article);
	List<Article> allArticleList(Article article);
	Article detailShareArticle(Article article);	// 댓글
	int readShareArticle(Article article);
	int writeShareArticle(Article article);	// 글쓰기

	// 백준
	Integer 			totalArticle(int brd_id);
	List<Article> 		articleTotal(Article article);
	Article 			detailContent(Article article);
	List<Article>		articleMenu(Article article);
	Integer 			upreadCount(Article article);
	List<Article> 		listMagnager();
	int 				bjWriteArticle(Article article);
	int 				bjUpdateArticle(Article article);
	int 				delete(Article article);
	int 				replyWrite(Reply reply);
	int					replyDelete(Reply reply);
	List<Article>		bjArtSearch(Article article);
	Integer				bjGood(Article article);
	Integer				bjBad(Article article);
	
	
	
	
	// 임동빈
	int 			dbtotalArticle(Article article);
	List<Article> 	dbListArticle(Article article);
	Article 		dbdetailArticle(Article article);
	List<Region> 	regionName();
	List<Region>	parentRegionName();
	List<Comm> 		categoryName();
	List<Comm>		genderName();
	List<Article> 	dbreplyList(Article article);
	void 			dbWriteArticle(Article article);
	void			dbUpdateArticle(Article article);
	int 			dbdeleteArticle(Article article);
	int				dbReadArticleCnt(Article article);
	Article 		dbReportArticle(Article article);
	Article 		dbReportReply(Article article);
	List<Article>   dbTradeJoinMember(Article article);
	List<Article> 	dbTradeWaitingMember(Article article);
	int 			dbTradeWaiting(Article article);
	int 			dbTradeJoinAccept(Article article);
	int 			dbTradeJoinRefuse(Article article);
	int 			dbJoinDelete(Article article);
	int 			dbfavoriteArticle(Article article);
	int				dbChangeStatus(Article article);
	int 			dbChangeEndStatus(Article article);
	int 			dbChangeCancelStatus(Article article);
	int 			dbArticleGoodUp(Article article);
	int 			dbArticleBadUp(Article article);
	int 			dbReplyGoodUp(Article article);
	int 			dbReplyBadUp(Article article);
	
	
	
	
	
	// 김진현
	List<Article_Trade_Reply> 			getDutchpayList(String boardName);//진현
	Article_Trade_Reply                 detail1(Article_Trade_Reply atr);
	List<Comm> 							category1();
	List<Region>         				loc1();
	void 								dutchpayInsert1(Article_Trade_Reply atr);
	Article_Trade_Reply		 			updateForm1(Article_Trade_Reply atr);
	List<Comm> 							category_ud1();
	List<Region> 						loc_ud1();
	
	// 김찬영
	int					totalArticle();
	List<Article> 		listArticle(Article article);
//	List<Article> 		listReply(Article article);
	Article				cyArticlereadDetail(Article article);
	Article 			cyArticlereadupdate(Article article);
	Article 			detailArticle(int art_title);
	int					cyArticleinsert(Article article);
	int					cyArticlemodify(Article article);
	public Article 		cyArticledelete(Article article);
	int 				updateView(Article article);
	int 				updateGood(Article article);
	int 				updateBad(Article article);
//	public int 			totalArticleSearch(Article article);
	
	// 최승환
	int 				totalCustomer();
	List<Article> 		listCustomer(Article article);
	Article 			detailCustomer(Article article);
	List<Article> 		listCustomerMenu(Article article);
	public int 			insertCustomer(Article article);

}