package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Report {
	private int 	report_id;
	private int 	mem_id;
	private String 	report_content;
	private Date 	report_date;
	private int 	report_status;
	private String 	report_reason;
}
