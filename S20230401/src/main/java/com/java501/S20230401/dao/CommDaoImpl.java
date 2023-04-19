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
	public List<Comm> commName() {
			List<Comm> CommList = null;
			System.out.println("CommDaoImpl CommName Start..");
			CommList = session.selectList("tkSelectComm");
			System.out.println("CommDaoImpl CommList.size()=> " + CommList.size());
			return CommList;
	}

}
