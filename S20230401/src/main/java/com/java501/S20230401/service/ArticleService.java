package com.java501.S20230401.service;

import java.util.List;

import javax.servlet.http.Cookie;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
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
	int allTotalArt(Article article); // 모든 글 카운트
	List<Article> allArticleList(Article article); // 모든 글 조회
	Article detailShareArticle(Article article);	// 댓글
	int readShareArticle(Article article); // 조회수 증가
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
	public int 			bjUpdateArticle(Article article);
	public int 			delete(Article article);
	
	// 임동빈
	int 			totalArticle(Article article);
	List<Article> 	dbListArticle(Article article);
	Article 		detailArticle(Article article);
	List<Region> 	regionName();
	List<Region>	parentRegionName();
	List<Comm> 		categoryName();
	List<Comm>		genderName();
	List<Article> 	replyList(Article article);
	void 			dbWriteArticle(Article article);
	void			dbUpdateArticle(Article article);
	int 			deleteArticle(Article article);
	
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
	int				totalArticle();
	List<Article> 	listArticle(Article article);
	Article			cyArticlereadDetail(Article article);
	Article 		cyArticlereadupdate(Article article);
	Article 		detailArticle(int art_title);
	int				cyArticleinsert(Article article);
	int				cyArticlemodify(Article article);
	
	// 최승환
	int 				totalCustomer();
	List<Article> 		listCustomer(Article article);
	Article 			detailCustomer(Article article);
	List<Article> 		listCustomerMenu(Article article);
	public int 			insertCustomer(Article article);


}