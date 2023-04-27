package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Article_Trade_Reply {

		//Article
	private Integer 	art_id;
	private Integer 	brd_id;
	private String 		art_title;
	private Integer 	mem_id;
	private Date 		art_regdate;
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
	private String 		art_content;
	
		//Trade
	private Integer 	trd_status;
	private Integer 	trd_max;
	private Date 		trd_enddate;
	private Integer 	trd_cost;
	private Integer 	reg_id;
	private String 		trd_loc;
	private Integer 	trd_gender;
	private Integer 	trd_minage;
	private Integer 	trd_maxage;

	

	
		//Reply
	private Integer 	rep_id;
	private String 		rep_content;
	private Date 		rep_regdate;
	private Integer 	rep_good;
	private Integer 	rep_bad;
	private Integer 	rep_parent;
	private Integer 	rep_step;
	
	
	
		//Comm
	private Integer 	comm_id;
	private String 		comm_value;
	
	
		//Region
	private String 		reg_name;
	private Integer 	reg_parent;
	
		//Member
	private String 		mem_username;
	private String 		mem_password;
	private String 		mem_nickname;
	private String 		mem_email;
	private String 		mem_tel;
	private Integer 	mem_region1;
	private Integer 	mem_region2;
	private Integer 	mem_authority;
	private Date 		mem_regdate;
	private String 		mem_image;
	private Integer 	mem_gender;
	private String 		mem_name;
	private Date 		mem_birthday;
	private Date 		mem_latest;
	
	
	private Number 		insert_result;
	private Integer		reply_count;
}
