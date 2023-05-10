package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Favorite;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteDaoImpl implements FavoriteDao {

	private final SqlSession session;
	@Override
	public int cyFavorite(Favorite favorite) {
		System.out.println("관심 다오 임플");
		int result = 0;
		try {
			result = session.insert("cyFavorite", favorite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
