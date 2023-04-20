package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Comm;

public interface CommDao {
	public String getValueById(int comm_id);

	public Comm getCommById(int comm_id);

	public Comm getCommByName(String comm_value);

	public List<Comm> getCategoryListBySuper(Integer superId);
}
