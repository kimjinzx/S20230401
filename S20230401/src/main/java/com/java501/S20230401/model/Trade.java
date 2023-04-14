package com.java501.S20230401.model;

import java.util.Date;

import lombok.Data;

@Data
public class Trade {
	private int 	trd_id;
	private int 	mem_id;
	private int 	trd_status;
	private int 	trd_max;
	private Date 	trd_enddate;
	private int 	trd_cost;
	private int 	reg_id;
	private String 	trd_loc;
	private int 	trd_gender;
	private int 	trd_minage;
	private int 	trd_maxage;
}
