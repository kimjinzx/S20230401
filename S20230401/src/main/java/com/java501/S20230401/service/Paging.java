package com.java501.S20230401.service;

import lombok.Data;

@Data
public class Paging {
	private int currentPage = 1;
	private int pageRow = 3;		// 한 페이지의 게시글 수
	private int pageBlock = 10;		// 한번에 보여 줄 페이지
	private int start;
	private int startPage;
	private int end;
	private int endPage;
	private int total;
	private int totalPage;
	
	public Paging(int total, String currentpage1) {
		this.total = total;
		if(currentpage1 != null)
			this.currentPage = Integer.parseInt(currentpage1); //2
		//					1				10
		start 		= (currentPage - 1) * pageRow + 1;	// 시작시 1		11
		end			= start + pageRow - 1;				//시작시 10	20
					//		25		/	10
		startPage	= currentPage - ((currentPage - 1) % pageBlock);
		endPage		= startPage + pageBlock - 1;
				// 공갈 Page 제거를 위한 로직
				//	10		14
		totalPage 	= (int)Math.ceil((double)total / pageRow);
		endPage		= endPage > totalPage? totalPage : endPage;
		
	}
}
