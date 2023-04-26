package com.java501.S20230401.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Article {
	private Integer art_id;			// PK
	private Integer brd_id;			// PK
	private Integer mem_id;			// FK
	private Integer trd_id;			// FK
	private String 	art_title;
	private String	art_content;
	private Date 	art_regdate;
	private String 	art_tag1;
	private String 	art_tag2;
	private String 	art_tag3;
	private String 	art_tag4;
	private String 	art_tag5;
	private Integer art_good;
	private Integer art_bad;
	private Integer art_read;
	private Integer art_isnotice;
	private Integer isdelete;
	private Integer report_id;
	
	// 양동균
	// 참조
	private Member 	member;
	private Board 	board;
	private Trade 	trade;
	// comm 테이블 - 식별
	private String 	brd_name; 	// 게시판 이름
	private String	gen_name; 	// 멤버 성별
	private String 	status_name;// 거래 상태
	private Integer rep_cnt;	// 댓글 갯수
	// Search용 필드
	private String 	search;
	private String 	keyWord;
	private String 	pageNum;
	private Integer start;
	private Integer end;
	
	
	// 백준
	private String 		mem_nickname;	
	private String 		mem_image;
}
