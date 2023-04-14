package com.java501.S20230401.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	private int currentPage = 1;
	private int rowPage = 10;
	private int pageBlock = 10;
	private int start;
	private int end;
	private int startPage;
	private int endPage;
	private int total;
	private int totalPage;
	
	public Paging(int total, String currentpage1) {
		this.total = total;	//
		if(currentpage1 != null) {
			this.currentPage = Integer.parseInt(currentpage1); //2
		}
		//			1				10
		start = (currentPage -1)* rowPage +1; // 시작시 1		11
		end = start + rowPage -1;				//시작시 10	20
						//				25		/	10
		totalPage = (int) Math.ceil((double)total/rowPage); //시작시 3	5	14
						//2			2
		startPage = currentPage -(currentPage -1 )% pageBlock;
		endPage = startPage + pageBlock -1; //10
		// 공갈 Page 제거를 위한 로직
		//	10		14
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
}
