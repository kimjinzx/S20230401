package com.java501.S20230401.dao;


import com.java501.S20230401.model.Article;

public interface FavoriteDao {

	void       favoriteInsert2(Article article);
	int        favoriteInsertYN2(Article article);

}
