package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java501.S20230401.dao.CommDao;
import com.java501.S20230401.model.Comm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommServiceImpl implements CommService {
	private final CommDao commDao;
	@Override
	public String categoryName(int comm_id) {
		return commDao.categoryName(comm_id);
	}
	@Override
	public List<Comm> commList(int comm_id) {
		return commDao.commList(comm_id);
	}

}
