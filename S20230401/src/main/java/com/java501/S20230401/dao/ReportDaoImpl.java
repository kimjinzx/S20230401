package com.java501.S20230401.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Report;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportDaoImpl implements ReportDao {

	private final SqlSession session;
		
	@Override
	public int cyReportinsert(Report report) {
		System.out.println("신고 다오임플 작성");
		int result=0;
		try {
			result = session.insert("cyReportinsert", report);
		} catch (Exception e) {
			System.out.println("신고임플 getMessage()->"+e.getMessage());
		}
		return result;
	}

}
