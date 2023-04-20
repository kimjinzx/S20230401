package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.CommDao;
import com.java501.S20230401.model.Comm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommServiceImpl implements CommService {
	private final CommDao cd;
	
	@Override
	public Comm getCommById(int comm_id) {
		return cd.getCommById(comm_id);
	}
	
	@Override
	public Comm getCommByName(String comm_value) {
		return cd.getCommByName(comm_value);
	}
	
	@Override
	public String getValueById(int comm_id) {
		return cd.getValueById(comm_id);
	}
	
	@Override
	public List<Comm> getCategoryListBySuper(Integer superId) {
		return cd.getCategoryListBySuper(superId);
	}
}
