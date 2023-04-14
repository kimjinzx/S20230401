package com.java501.S20230401.model;

import lombok.Data;

@Data
public class Board {
	private int brd_id;
	private int read_auth;
	private int write_auth;
	private int reply_auth;
	
}
