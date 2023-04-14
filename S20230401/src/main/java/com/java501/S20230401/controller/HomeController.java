package com.java501.S20230401.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 메인 페이지 계열 컨트롤러 : 유현규
@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public String index() {
		
		return "index";
	}
}
