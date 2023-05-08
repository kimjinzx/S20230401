package com.java501.S20230401.service;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ReportDao;
import com.java501.S20230401.model.Report;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final ReportDao pd;
	@Override
	public int cyReportinsert(Report report) {
		System.out.println("신고 작성 서비스임플");
		int result = pd.cyReportinsert(report);
		return result;
	}

}
