package com.java501.S20230401.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.dao.ReplyDao;
import com.java501.S20230401.model.Reply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	private final ReplyDao rd;

}