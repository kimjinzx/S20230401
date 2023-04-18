package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Comm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommDaoImpl implements CommDao {
	private final SqlSession session;
	
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
}
