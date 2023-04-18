package com.java501.S20230401.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.service.RegionService;
import com.java501.S20230401.util.MemberSearchKeyword;

import lombok.RequiredArgsConstructor;

// 유저 페이지 계열 컨트롤러 : 유현규
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService ms;
	private final RegionService rs;
	
	@RequestMapping(value = "/login")
	public String memberLogin() {
		
		return "loginForm";
	}
	
	@RequestMapping(value = "/join")
	public String memberJoin(Model model) {
		model.addAttribute("now", new Date());
		
		Map<Region, List<Region>> regionHierachy = new HashMap<Region, List<Region>>();
		List<Region> superRegions = rs.getSuperRegions();
		for (Region sups : superRegions) regionHierachy.put(sups, rs.getChildRegions(sups.getReg_id()));
		model.addAttribute("superRegions", superRegions);
		model.addAttribute("regions", regionHierachy);
		
		return "joinForm";
	}
	
	@PostMapping(value = "/joinProc")
	public String memberJoinProcess(@RequestParam MultipartFile file, HttpServletRequest request) {
		/*log.info("request={}", request);
        log.info("itemName={}", itemName);
        log.info("multipartFile={}", file);

        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }*/
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping(value = "/getMember")
	public Map<String, Object> getMember(@RequestBody Map<String, Object> data){
		Map<String, Object> resp = new HashMap<String, Object>();
		String type = (String)data.get("type");
		String value = (String)data.get("value");
		MemberSearchKeyword searchType = Enum.valueOf(MemberSearchKeyword.class, type.toUpperCase());
		Member member = ms.getMember(value, searchType);
		resp.put("value", member);
		return resp;
	}
}
