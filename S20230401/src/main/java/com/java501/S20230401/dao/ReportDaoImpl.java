package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Report;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportDaoImpl implements ReportDao {
	private final SqlSession session;
	
	// 양동균
	@Override
	public int shareReport(Report report) {
		int result = 0;
		try {
			result = session.insert("dgShareReport", report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
