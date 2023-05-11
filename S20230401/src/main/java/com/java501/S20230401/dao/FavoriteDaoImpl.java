package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteDaoImpl implements FavoriteDao {
	private final SqlSession session;

	@Override
	public void favoriteInsert2(Article article) {
		session.insert("JHFavoriteInsert",article);
	}

	@Override
	public int favoriteInsertYN2(Article article) {
		return session.selectOne("JHFavoriteInsertYN",article);
	}

}
