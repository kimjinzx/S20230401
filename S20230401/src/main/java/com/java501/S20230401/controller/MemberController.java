package com.java501.S20230401.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java501.S20230401.model.Member;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.service.CommService;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.service.RegionService;
import com.java501.S20230401.util.EmailMessage;
import com.java501.S20230401.util.MemberSearchKeyword;

import lombok.RequiredArgsConstructor;

// 유저 페이지 계열 컨트롤러 : 유현규
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService ms;
	private final RegionService rs;
	private final CommService cs;
	
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
	public String memberJoinProcess(@RequestParam(value = "image-file", required = false) MultipartFile file, @RequestParam Map<String, String> params, MultipartHttpServletRequest request, RedirectAttributes redirectAttributes) {
		Member member = new Member();
		member.setMem_username(params.get("username"));
		member.setMem_password(new BCryptPasswordEncoder().encode(params.get("password")));
		member.setMem_nickname(params.get("nickname"));
		member.setMem_email(params.get("email"));
		member.setMem_tel(params.get("tel1") + params.get("tel2") + params.get("tel3"));
		int year = Integer.parseInt(params.get("year"));
		int month = Integer.parseInt(params.get("month"));
		int day = Integer.parseInt(params.get("day"));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		Date birthday = calendar.getTime();
		member.setMem_birthday(birthday);
		member.setMem_gender(cs.getCommByName(params.get("gender")).getComm_id());
		String[] regions = { params.get("region1-value").trim(), params.get("region2-value").trim() };
		for (String reg : regions) {
			if (!reg.trim().isEmpty()) {
				int regionCode = Integer.parseInt(reg);
				if (member.getMem_region1() == null) member.setMem_region1(regionCode);
				else member.setMem_region2(regionCode);
			}
		}
		String savedName = file.getOriginalFilename();
		String realPath = request.getSession().getServletContext().getRealPath("/uploads/profile");
		try {
			savedName = uploadFile(realPath, savedName, file.getBytes());
			member.setMem_image(savedName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = ms.registMember(member);
		Member readMember = ms.getMember(member.getMem_username(), MemberSearchKeyword.USERNAME);
		readMember.setMem_password(null);
		redirectAttributes.addFlashAttribute("member", readMember);
		return "redirect:/mail/JoinAuthentification";
	}
	
	private String uploadFile(String realPath, String originalName, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString() + "_" + originalName;
		File target = new File(realPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
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
