package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Comm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommDaoImpl implements CommDao {
	private final SqlSession session;
	
	
	@Override
	public String categoryName(int comm_id) {
		String categoryName = "";
		try {
			categoryName = session.selectOne("dgCategoryName", comm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryName;
	}

	@Override
	public List<Comm> commList(int comm_id) {
		List<Comm> commList = null;
		try {
			commList = session.selectList("dgCommList", comm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commList;
	}

}
