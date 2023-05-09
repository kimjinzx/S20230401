package com.java501.S20230401.service;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.ReportDao;
import com.java501.S20230401.model.Report;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final ReportDao rd;
	
	// 양동균
	@Override
	public int shareReport(Report report) { return rd.shareReport(report); }

}
