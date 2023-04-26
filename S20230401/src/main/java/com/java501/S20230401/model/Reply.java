package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	private int 	art_id;
	private int 	brd_id;
	private int 	rep_id;
	private int 	mem_id;
	private String 	rep_content;
	private Date 	rep_regdate;
	private int 	rep_good;
	private int 	rep_bad;
	private int 	rep_parent;
	private int 	rep_step;
	private int 	isdelete;
	private int 	report_id;
	
	
}
