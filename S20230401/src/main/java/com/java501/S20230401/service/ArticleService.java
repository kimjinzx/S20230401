package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

public interface ArticleService {

	List<Article_Trade_Reply> 			getDutchpayList(String boardName);
	Article_Trade_Reply                 detail1(Article_Trade_Reply atr);
	List<Comm> 							category1();
	List<Region>         				loc1();
	void 								dutchpayInsert1(Article_Trade_Reply atr);
	Article_Trade_Reply		 			updateForm1(Article_Trade_Reply atr);
	List<Comm> 							category_ud1();
	List<Region> 						loc_ud1();
}