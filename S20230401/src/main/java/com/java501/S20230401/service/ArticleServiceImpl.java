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
	public Integer totalNotice() {
		System.out.println("ArticleServiceImpl Start total..." );
		int totNoticeCnt = ad.totalNotice();
		System.out.println("ArticleServiceImpl totalNotice totNoticeCnt->" + totNoticeCnt);
		return totNoticeCnt;
	}

	@Override
	public List<Article> listNotice(Article article) {
		 List<Article> noticeList = null;
		 System.out.println("ArticleServiceImpl listManager Start..." );
		 noticeList = ad.listNotice(article);
		 System.out.println("ArticleServiceImpl listEmp noticeList.size()->" +noticeList.size());
		 return noticeList;
	}

	@Override
	public Article detailNotice(Article article) {
		System.out.println("ArticleServiceImpl detail...");
		Article noticeDetail = null;
		noticeDetail = ad.detailNotice(article);
		return noticeDetail;
	}

}
