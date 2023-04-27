package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Comm;

public interface CommService {
	// 현재 카테고리 이름 식별
	String categoryName(int comm_id);
	// 카테고리 목록 가져오기
	List<Comm> commList(int comm_id);
	
	public Comm getCommById(int comm_id);
	public Comm getCommByName(String comm_value);
	public String getValueById(int comm_id);
	public List<Comm> getCategoryListBySuper(Integer superId);

}
