package com.java501.S20230401.dao;

import java.util.List;


import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
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
	
	
	
	
	// 김찬영
	int				totalArticle();								//총 리스트
	List<Article> 	listArticle(Article article);				//리스트
	Article			cyArticlereadDetail(Article article);		//상세페이지
	Article 		cyArticlereadupdate(Article article);		//수정페이지 상세
	Article 		detatilArticle(int art_title);				//상세페이지
	int				cyArticleinsert(Article article);			//게시물 작성
	int				cyArticlemodify(Article article);			//게시물 수정
	
	
	
	
	// 최승환
	int				totalCustomer();
	List<Article> 	listCustomer(Article article);
	Article 		detailCustomer(Article article);
	List<Article> 	listCustomerMenu(Article article);
	int 			insertCustomer(Article article);

	
	
	// 김진현
	List<Article> 			getDutchpayList(String boardName);
	Article                 detail2(Article article);
	public List<Article> 	repList2(Article article);
	public List<Comm>       payStatus2();
	public Article 			payStatusPro2(Article article);
	List<Comm>           	category2();
	List<Region>    		loc2();
	public void 			dutchpayInsert2(Article article);
	Article 				updateForm2(Article article);
	List<Region> 			loc_ud2();
	void 					dutchpayUpdate2(Article article);
	void 					dutchpayDelete2(Article article);
	public int 				DeatilRead2(Article article);
	public int 				totalArticle2();
	void                    applyInsert2(Article article);
	public Article 			applyCancel2(Article article);
	public Article 			joinCancel2(Article article);
	public List<Article>    joinList2(Article article);
	public List<Article>    waitList2(Article article);
	public Article 			joinDeny2(Article article);
	public Article          joinAccept2(Article article);
	public int              payCompleted2(int trd_id);
	
}