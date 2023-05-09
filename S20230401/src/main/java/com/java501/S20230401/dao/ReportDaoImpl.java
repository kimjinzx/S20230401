package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Report;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportDaoImpl implements ReportDao {
	private final SqlSession session;
	
	// 유현규
	@Override
	public int hgGetCountAllUnprocessedReports() {
		return session.selectOne("hgGetCountAllUnprocessedReports");
	}
	 @Override
	public List<Report> hgGetAllUnprocessedReports(Report report) {
		return session.selectList("hgGetAllUnprocessedReports", report);
	}
}
