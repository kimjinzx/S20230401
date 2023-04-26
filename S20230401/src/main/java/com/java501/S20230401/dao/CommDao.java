package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Comm;

@Repository
public interface CommDao {
	public String getValueById(int comm_id);


	public Comm getCommById(int comm_id);

	public Comm getCommByName(String comm_value);

	public List<Comm> getCategoryListBySuper(Integer superId);

	// 여기서부터 내꺼
	List<Comm> boardName();
	List<Comm> genderName();
}
