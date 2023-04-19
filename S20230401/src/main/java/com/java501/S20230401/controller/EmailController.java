package com.java501.S20230401.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java501.S20230401.model.Authentications;
import com.java501.S20230401.model.Member;
import com.java501.S20230401.service.AuthenticationsService;
import com.java501.S20230401.service.EmailService;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.util.EmailMessage;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmailController {
	private final EmailService es;
	private final MemberService ms;
	private final AuthenticationsService as;
	
	@RequestMapping(value = "/mail/JoinAuthentification")
	public String sendJoinAuth(HttpServletRequest request) {
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		Member member = (Member)flashMap.get("member");
		String emailAddress = member.getMem_email();
		EmailMessage msg = EmailMessage.builder().to(emailAddress).subject("[ShareGo] 회원가입 인증을 완료해주세요").build();
		String contextPath = request.getSession().getServletContext().getContextPath();
		String code = es.sendMail(msg, "thymeleaf/join", contextPath);
		as.setAuthentication(member, code);
		return "redirect:/";
	}
	
	@GetMapping(value = "/MemberAuthentication")
	public String authentication(@RequestParam String v) {
		Authentications auth = as.getAuthentication(v);
		if (auth != null) {
			ms.setAuthority(auth.getMem_id(), 103);
		}
		as.deleteAuthentication(auth.getAuth_id());
		return "redirect:/";
	}
}
