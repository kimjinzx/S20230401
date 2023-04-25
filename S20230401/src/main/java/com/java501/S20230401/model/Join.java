package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Join {
	private Integer  trd_id;
	private Integer  mem_id;
	private Date join_date;
}
