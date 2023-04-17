package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Article {
	// ARTICLE
	private int 	art_id;
	private int 	brd_id;
	private String 	art_title;
	private int 	mem_id;
	private Date 	art_regdate;
	private String  art_content;
	private String 	art_tag1;
	private String 	art_tag2;
	private String 	art_tag3;
	private String 	art_tag4;
	private String 	art_tag5;
	private int 	trd_id;
	private int 	art_good;
	private int 	art_bad;
	private int 	art_read;
	private int 	art_isnotice;
	private int 	isdelete;
	private int 	report_id;
	
	// COMM
	private int 	comm_id;
	private String 	comm_value;
	
	// TRADE
	private int 	trd_status;
	private int 	trd_max;
	private Date 	trd_enddate;
	private int 	trd_cost;
	private int 	reg_id;
	private String 	trd_loc;
	private int 	trd_gender;
	private int 	trd_minage;
	private int 	trd_maxage;
	
	// member

	private String 	mem_username;
	private String 	mem_password;
	private String 	mem_nickname;
	private String 	mem_email;
	private String 	mem_tel;
	private int 	mem_region1;
	private int 	mem_region2;
	private int 	mem_authority;
	private Date 	mem_regdate;
	private String 	mem_image;
	private int 	mem_gender;
	private String 	mem_name;
	private Date 	mem_birthday;
	private Date 	mem_latest;

	// reply
	
	private int 	rep_id;
	private String 	rep_content;
	private Date 	rep_regdate;
	private int 	rep_good;
	private int 	rep_bad;
	private int 	rep_parent;
	private int 	rep_step;
	
	// PAGING
	private String pageNum;
	private int start;
	private int end;
	
	// 조회용
	private int repCount;
}
