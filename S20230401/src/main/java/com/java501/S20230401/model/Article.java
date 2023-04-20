package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Article {
	private int 	art_id;
	private int 	brd_id;
	private String 	art_title;
	private String	art_content;
	private int 	mem_id;
	private Date 	art_regdate;
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
}
