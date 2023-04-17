package com.java501.S20230401.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Article {
	private int 	art_id;
	private int 	brd_id;
	private String 	art_title;
	private int 	mem_id;
	private Date 	art_regdate;
	private String	art_content;
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
	
	// 참조
	private Member 	member;
	private Board 	board;
	private Trade 	trade;
	
	
	// 1 : N 관계
	private List<Reply> reply;
	
	// Search용 필드
	private String 	search;
	private String 	keyWord;
	private String 	pageNum;
	private int 	start;
	private int 	end;
}
