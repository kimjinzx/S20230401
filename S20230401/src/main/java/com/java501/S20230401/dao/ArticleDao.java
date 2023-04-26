package com.java501.S20230401.dao;

import java.util.List;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleDao {
	int				totalArticle();								//총 리스트
	List<Article> 	listArticle(Article article);				//리스트
	Article			cyArticlereadDetail(Article article);		//상세페이지
	Article 		cyArticlereadupdate(Article article);		//수정페이지 상세
	Article 		detatilArticle(int art_title);				//상세페이지
	int				cyArticleinsert(Article article);			//게시물 작성
	int				cyArticlemodify(Article article);			//게시물 수정
	
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	public int insertArticle(Article article);

	public Article getArticleById(Article searcher);

}
