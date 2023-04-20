package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Comm;

public interface CommService {
	public Comm getCommById(int comm_id);
	public Comm getCommByName(String comm_value);
	public String getValueById(int comm_id);
	public List<Comm> getCategoryListBySuper(Integer superId);
}
