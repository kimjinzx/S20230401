package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao{
	private final SqlSession session;
	
	// 페이징 작업 전체 글 갯수 가져오기
	@Override
	public Integer totalArticle(int brd_id) {
		int totArticleCount = 0;
		try {
			if (brd_id % 100 == 0) totArticleCount = session.selectOne("bjarticleIndex", brd_id);
			else totArticleCount = session.selectOne("bjarticlePart", brd_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return totArticleCount;
	}

	@Override
	public List<Article> articleTotal(Article article) {
		List<Article> articleList = null;
		
		try {
			articleList = session.selectList("bjarticleIndexAll",article);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return articleList;
	}

	@Override
	public Article detailContent(Article article) {
		Article detailCon = new Article();
		
		try {
			detailCon = session.selectOne("bjdetailContent",article);
		} catch (Exception e) {
			System.out.println("에러"+e.getMessage());
		}
		return detailCon;
	}
	
	@Override
	public List<Article> artcleMenu(Article article) {
		List<Article> articleMenu = null;
		try {
			articleMenu = session.selectList("bjArticleMenu",article);
		} catch (Exception e) {
			System.out.println("게시판 메뉴에러"+e.getMessage());
		}
		return articleMenu;
	}

	@Override
	public Integer replyCount(int art_id) {
		int countReply = 0;
		try {
			countReply = session.selectOne("bjCountReply", art_id);
		} catch (Exception e) {
			System.out.println("리플 에러"+e.getMessage());
		}
		
		return countReply;
	}

	@Override
	public Integer upreadCount(Article article) {
		Article countUpread = new Article();
		Integer count = 0;
		try {
			count = session.update("bjreadCount",countUpread);
			System.out.println("업데이트 결과 "+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Article> listMagnager() {
		List<Article> bjwrite = null;
		try {
			bjwrite = session.selectList("bjWriteArticle");
		} catch (Exception e) {
			System.out.println("에러->"+e.getMessage());
		}
		return bjwrite;
	}

	@Override
	public int writeArticle(Article article) {
		int writeArticle = 0;
		try {
			writeArticle = session.insert("bjWriteArticle",article);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return writeArticle;
	}

}
