package com.java501.S20230401.service;

import lombok.Data;

@Data
public class Paging {
	private int currentPage = 1;	// 현재 페이지
	private int pageRow = 3;		// 한 페이지의 게시글 수
	private int pageBlock = 10;		// 페이징 라인에 보여지는 페이지 개수
	private int start;		// 시작 행
	private int startPage; 	// 첫 페이지
	private int end;		// 마지막 행
	private int endPage; 	// 마지막 페이지
	private int total;		// 전체 행 수
	private int totalPage;	// 페이지 전체 개수
	
	public Paging(int total, String presentPage) {
		this.total = total;
		if(presentPage != null) this.currentPage = Integer.parseInt(presentPage); 
		
		// 1(2,3)    4(5,6)    7(8,9)    10(11,12)    13(14,15)   16(17,18)  ...   
		start 		= (currentPage - 1) * pageRow + 1;
		
		// (1,2)3    (5,6)7	   (7,8)9    (10,11)12   (13,14)15    (16,17)18 ...
		end			= start + pageRow - 1;
		
		//  1          
		startPage	= currentPage - ((currentPage - 1) % pageBlock);
		//  10
		endPage		= startPage + pageBlock - 1;
		//  total이 59일 경우  5.9  = 5
		totalPage 	= (int)Math.ceil((double)total / pageRow);
		
		endPage		= endPage > totalPage? totalPage : endPage; //가짜 page제거
		
	}
	
	
}
