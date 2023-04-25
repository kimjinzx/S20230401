package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Article {
	private Integer 	art_id;
	private Integer 	brd_id;
	private String 		art_title;
	private Integer 	mem_id;
	private Date 		art_regdate;
	private String 		art_content;
	private String 		art_tag1;
	private String 		art_tag2;
	private String 		art_tag3;
	private String 		art_tag4;
	private String 		art_tag5;
	private Integer 	trd_id;
	private Integer 	art_good;
	private Integer 	art_bad;
	private Integer 	art_read;
	private Integer 	art_isnotice;
	private Integer 	isdelete;
	private Integer 	report_id;
	
	// 조인
	private String 		mem_nickname;
	private String 		mem_image;
	private Integer		rep_count;
	
	//조회용
	private String 		pageNum;
	
	private Integer 	start;
	private Integer 	end;
}
