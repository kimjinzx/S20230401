package com.java501.S20230401.service;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.FavoriteDao;
import com.java501.S20230401.model.Favorite;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
	private final FavoriteDao fd;
	
	@Override
	public int cyFavorite(Favorite favorite) {
		System.out.println("관심서버 임플 insert");
		int result = fd.cyFavorite(favorite);
		return result;
	}

}
