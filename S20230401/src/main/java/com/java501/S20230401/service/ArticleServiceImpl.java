package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ArticleDao;
import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleDao	ad;
	
	@Override
	public int totalArticle() {
		System.out.println("ArticleServiceImpl Start total..." );
		int totArticleCnt = ad.totalArticle();
		System.out.println("ArticleServiceImpl totalEmp totEmpCnt->" + totArticleCnt);
		return totArticleCnt;
	}

	@Override
	public List<Article> listArticle(Article article) {
		 List<Article> articleList = null;
		 System.out.println("ArticleServiceImpl listManager Start..." );
		 articleList = ad.listArticle(article);
		 System.out.println("ArticleServiceImpl listEmp articleList.size()->" +articleList.size());
		 return articleList;
	}

}
