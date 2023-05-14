package com.java501.S20230401.service;

import java.util.List;

import javax.servlet.http.Cookie;

import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.Waiting;
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
	public List<ArticleMember> hgGetArticlesOfMember(int mem_id);
	public int hgIncreaseReadCount(Article searcher);
	public int hgRecommendArticle(Article searcher);
	public int hgCompressedUpdateArticle(Article article);
	public int hgDeleteArticle(Article article);
	public int hgRestoreArticle(Article article);
	public int hgInsertAdminArticle(Article article);
	public List<Article> hgAdminArticleList(Article searcher);

	// 양동균
	int allTotalArt(Article article);
	List<Article> allArticleList(Article article);
	Article detailShareArticle(Article article);	// 댓글
	int readShareArticle(Article article);
	int writeShareArticle(Article article);	// 글쓰기
    int dgDeleteArticle(Article article); // 글삭제
	int dgVoteGood(Article article); // 추천
	int dgVoteBad(Article article); // 비추천
	int dgReportArticle(Article article); // 신고
	
	
	

	

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
	int 				totalCustomer(Article article);
	List<Article> 		listCustomer(Article article);
	Article 			detailCustomer(Article article);
//	List<Article> 		listCustomerMenu(Article article); // 넌 뭐냐
	int		 			insertCustomer(Article article);
	int 				updateCustomer(Article article);
	int 				deleteCustomer(Article article);
	Integer 			customerViewCount(Article article);
	List<Article> 		shSearchCustomer(Article article);
	int 				customLike(Article article);
	int 				customDislike(Article article);
	
	
	// 김진현
	List<Article> 			getDutchpayList(String boardName);//진현
	Article                 detail1(Article article);
	public List<Article> 	repList1(Article article);
	public List<Comm>		payStatus1();
	public Article 			payStatusPro1(Article article);
	public void	            replyInsert1(Article article);
	public void             replyDelete1(Article article);
	List<Comm> 				category1();
	List<Region>         	loc1();
	void 					dutchpayInsert1(Article article);
	Article		 			updateForm1(Article article);
	List<Region> 			loc_ud1();
	public void 			dutchpayUpdate1(Article article);
	public void 			dutchpayDelete1(Article article);
	public int 				DeatilRead1(Article article);
	public int 				totalArticle1();
	void 			        applyInsert1(Article article);
	public Article 			applyCancel1(Article article);
	public Article			joinCancel1(Article article);
	public List<Article>    joinList1(Article article);
	public List<Article>    waitList1(Article article);
	public Article 			joinDeny1(Article article);
	public Article          joinAccept1(Article article);
	public int              payCompleted1(int trd_id);
	public int              jhJoinListYN(Article article);
	public int              jhWaitListYN(Article article);

	
	
	
}