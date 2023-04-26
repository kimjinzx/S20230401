package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	private Integer 	art_id;
	private Integer 	brd_id;
	private Integer 	rep_id;
	private Integer 	mem_id;
	private String 		rep_content;
	private Date 		rep_regdate;
	private Integer 	rep_good;
	private Integer 	rep_bad;
	private Integer 	rep_parent;
	private Integer 	rep_step;
	private Integer 	isdelete;
	private Integer 	report_id;
}
