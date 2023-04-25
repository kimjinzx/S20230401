package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

@Repository
public interface ArticleDao {

	List<Article_Trade_Reply> 			getDutchpayList(String boardName);
	Article_Trade_Reply                 detail2(Article_Trade_Reply atr);
	void 								dutchpayInsert2(Article_Trade_Reply atr);
	List<Comm>           				category2();
	List<Region>    			        loc2();
	Article_Trade_Reply 				updateForm2(Article_Trade_Reply atr);
	List<Comm> 							category_ud2();
	List<Region> 						loc_ud2();

}
