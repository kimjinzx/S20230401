package com.java501.S20230401.service;

import java.util.List;

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
	
	// 유현규
	@Override
	public int hgGetCountAllUnprocessedReports() {
		return pd.hgGetCountAllUnprocessedReports();
	}
	@Override
	public List<Report> hgGetAllUnprocessedReports(Report report) {
		return pd.hgGetAllUnprocessedReports(report);
	}
	@Override
	public Object hgGetInstanceByReportId(int report_id, String pascalClassName) {
		return pd.hgGetInstanceByReportId(report_id, pascalClassName);
	}
}
