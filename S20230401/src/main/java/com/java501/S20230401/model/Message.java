package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Message {
	private int 	mes_id;
	private int 	mem_sender_id;
	private int 	mem_receiver_id;
	private String 	mes_title;
	private String 	mes_content;
	private Date 	mes_regdate;
	private int 	mes_isread;
	private int 	mes_status;
	private int 	isdelete;
}
