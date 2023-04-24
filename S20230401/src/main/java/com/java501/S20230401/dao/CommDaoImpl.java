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

	// 로그인 기능
	@Override
	public Comm getCommById(int comm_id) {
		return session.selectOne("hgGetCommById", comm_id);
	}
	@Override
	public Comm getCommByName(String comm_value) {
		return session.selectOne("hgGetCommByName", comm_value);
	}
	@Override
	public String getValueById(int comm_id) {
		return session.selectOne("hgCommGetValue", comm_id);
	}
	@Override
	public List<Comm> getCategoryListBySuper(Integer superId) {
		return session.selectList("hgGetCategoryListBySuper", superId);
	}

}
