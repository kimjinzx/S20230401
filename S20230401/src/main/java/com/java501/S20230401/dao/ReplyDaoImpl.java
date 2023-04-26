package com.java501.S20230401.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class ReplyDaoImpl implements ReplyDao {

	private final SqlSession session;
	


}
