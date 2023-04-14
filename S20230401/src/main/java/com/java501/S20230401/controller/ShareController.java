package com.java501.S20230401.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

// 나눔해요 페이지 계열 컨트롤러 : 양동균
@Controller
@RequiredArgsConstructor
public class ShareController {
	
	// 나눔해요
	@RequestMapping(value = "share/total")
	public String totalPage() {
		return "share/total";
	}
	// 나눔해요 - 식품
	@RequestMapping(value = "share/food")
	public String foodPage() {
		return "share/food";
	}
	// 나눔해요 - 패션/잡화
	@RequestMapping(value = "share/fashion")
	public String fashionPage() {
		return "share/fashion";
	}
	// 나눔해요 - 가전/가구
	@RequestMapping(value = "share/appliances")
	public String appliancesPage() {
		return "share/appliances";
	}
	// 나눔해요 - 기타
	@RequestMapping(value = "share/etc")
	public String etcPage() {
		return "share/etc";
	}
}
