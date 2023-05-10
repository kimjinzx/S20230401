package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ReportDao;
import com.java501.S20230401.model.Report;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final ReportDao repod;
	
	// 유현규
	@Override
	public int hgGetCountAllUnprocessedReports() {
		return repod.hgGetCountAllUnprocessedReports();
	}
	@Override
	public List<Report> hgGetAllUnprocessedReports(Report report) {
		return repod.hgGetAllUnprocessedReports(report);
	}
	@Override
	public Object hgGetInstanceByReportId(int report_id, String pascalClassName) {
		return repod.hgGetInstanceByReportId(report_id, pascalClassName);
	}
}
