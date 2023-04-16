package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommDaoImpl implements CommDao {
	private final SqlSession session;
	
	@Override
	public String getValueById(int comm_id) {
		return session.selectOne("hgCommGetValue", comm_id);
	}
}
