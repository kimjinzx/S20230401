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
	public List<Comm> boardName() {
			List<Comm> BoardList = null;
			System.out.println("CommDaoImpl CommName Start..");
			BoardList = session.selectList("SelectBoard");
			System.out.println("CommDaoImpl CommList.size()=> " + BoardList.size());
			return BoardList;
	}
	
	@Override
	public List<Comm> genderName() {
			List<Comm> GenderList = null;
			System.out.println("CommDaoImpl CommName Start..");
			GenderList = session.selectList("SelectGender");
			System.out.println("CommDaoImpl CommList.size()=> " + GenderList.size());
			return GenderList;
	}

}
