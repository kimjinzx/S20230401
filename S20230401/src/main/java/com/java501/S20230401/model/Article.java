package com.java501.S20230401.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Article {
	private Integer art_id;
	private Integer brd_id;
	private String 	art_title;
	private Integer mem_id;
	private Date 	art_regdate;
	private String	art_content;
	private String 	art_tag1;
	private String 	art_tag2;
	private String 	art_tag3;
	private String 	art_tag4;
	private String 	art_tag5;
	private Integer trd_id;
	private Integer art_good;
	private Integer art_bad;
	private Integer art_read;
	private Integer art_isnotice;
	private Integer isdelete;
	private Integer report_id;
	
	// 참조
	private Member 	member;
	private Board 	board;
	private Trade 	trade;
	
	
	// 1 : N 관계
	private List<Reply> reply;
	
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
}
