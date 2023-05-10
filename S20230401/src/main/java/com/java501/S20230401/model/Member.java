package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private Integer mem_id;			// PK
	private Integer mem_region1;	// FK
	private Integer mem_region2;	// FK
	private Integer mem_authority;	// FK
	private Integer mem_gender;		// FK
	private String 	mem_username;
	private String 	mem_password;
	private String 	mem_nickname;
	private String 	mem_email;
	private String 	mem_tel;
	private Date 	mem_regdate;
	private String 	mem_image;
	private String 	mem_name;
	private Date 	mem_birthday;
	private Date 	mem_latest;
	private Integer isdelete;
	private Integer report_id;
	
	// 양동균
	// 참고
	private Comm 	comm;
	
	
	// 임동빈
	private String pageNum;
	private Integer start;
	private Integer end;
}