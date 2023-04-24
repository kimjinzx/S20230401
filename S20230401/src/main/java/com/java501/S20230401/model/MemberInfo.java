package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class MemberInfo {
	// Member data
	private int 	mem_id;
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
	private int 	isdelete;
	private int 	report_id;
	
	// Article data
	private int art_count;
	
	// Reply data
	private int rep_count;
	
	// Trade data
	private int his_good;
	private int his_normal;
	private int his_bad;
}
