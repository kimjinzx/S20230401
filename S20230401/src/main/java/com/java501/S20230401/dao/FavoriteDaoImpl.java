package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Favorite;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteDaoImpl implements FavoriteDao{
	private final SqlSession session;

	
	
	
	// 양동균
	// 찜목록 조회
	@Override
	public int dgFavorite(Favorite favorite) {
		int result = 0;
		try {
			result = session.selectOne("dgFavorite", favorite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 찜목록 추가
	@Override
	public int shareFavoriteAdd(Article article) {
		int result = 0;
		try {
			result = session.insert("dgShareFavoriteAdd", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 찜 삭제
	@Override
	public int shareFavoriteDel(Article article) {
		int result = 0;
		try {
			result = session.delete("dgShareFavoriteDel", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}




}
