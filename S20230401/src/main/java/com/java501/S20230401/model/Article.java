package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Article {
	private int 	art_id;
	private int 	brd_id;
	private String 	art_title;
	private Integer	mem_id;
	private Date 	art_regdate;
	private String 	art_tag1;
	private String 	art_tag2;
	private String 	art_tag3;
	private String 	art_tag4;
	private String 	art_tag5;
	private Integer	trd_id;
	private Integer	art_good;
	private Integer	art_bad;
	private Integer	art_read;
	private Integer	art_isnotice;
	private Integer	isdelete;
	private Integer	report_id;
	private String art_content;		 
	
	// Member
	private String mem_nickname;
	
	//조회용(강의내용)
	private String search;
	private String keyword;
	private String pageNum;
	private Integer start;
	private Integer end;
}
