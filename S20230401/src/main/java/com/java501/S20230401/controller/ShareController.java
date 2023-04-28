package com.java501.S20230401.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.MemberInfo;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.CommService;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.RegionService;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

// 나눔해요 페이지 계열 컨트롤러 : 양동균
@Controller
@RequiredArgsConstructor
@Slf4j
public class ShareController {
	private final ArticleService articleService;
	private final MemberService memberService;
	private final CommService commService;
	private final ReplyService replyService;
	private final RegionService regionService;
	
	
	// 나눔해요 글 목록
	@RequestMapping(value = "board/share")
	public String totalPage(@AuthenticationPrincipal
							MemberDetails memberDetails,	// 세션의 로그인 유저 정보
							Article article,
							Integer category,
							String currentPage,
							Model model) {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		// brd_id 할당
		article.setBrd_id(category);
		int totalArt = 0;
		List<Article> articleList = null;
		
		// 접속한 게시판의 카테고리 목록 가져오기
		List<Comm> commList = commService.commList((category / 100 * 100));
		// 접속한 카테고리 이름 식별
		String categoryName = commService.categoryName(category);
		
		// 페이징 작업 (게시글 수)
		totalArt = articleService.allTotalArt(article);
		Paging page = new Paging(totalArt, currentPage);
		article.setStart(page.getStart());
		article.setEnd(page.getEnd());
		
		// 게시글 조회
		articleList = articleService.allArticleList(article);
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("page", page);
		model.addAttribute("totalArt", totalArt);
		model.addAttribute("category", category);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("commList", commList);
		
		return "share/total";
	}
	
	
//	
//	
//	public int dgCheck() {
//		// url 데이터 변환
//		
//		int dgCheck(int art_id, int, brd_id, int mem_id){
//			
//		}
//		article.setArt_id(Integer.parseInt(art_id));
//
//		// 쿠키용 조회글 저장
//		String visitArticle = ""+article.getArt_id()+article.getBrd_id();
//
//		// 로그인 후 유저 정보
//		MemberInfo memberInfo = null;
//		if(memberDetails != null) {
//			memberInfo = memberDetails.getMemberInfo();
//			visitArticle += Integer.toString(memberInfo.getMem_id()); // 중복 방지 유저 id 추가 저장
//			model.addAttribute("memberInfo", memberInfo);
//		}
//		
//		
//		// 조회수 로직
//		Cookie oldCookie = null;
//		int result = 0;
//		// 쿠키 중복 체크
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null) {
//			for(Cookie cookie : cookies) {
//				log.info("쿠키 이름 {} , 쿠키 값{}",cookie.getName(), cookie.getValue());
//				if (cookie.getName().equals("visitArticle")) {
//					oldCookie = cookie;
//				}
//			}
//		}
//		// 처음 방문하거나(기존 쿠키 없음), 처음 조회 하는 글일 경우 실행 
//		if(oldCookie == null) {
//			Cookie newCookie = new Cookie("visitArticle", visitArticle);
//			newCookie.setMaxAge(60*60*2); // 쿠키 생명주기 2시간
//			newCookie.setPath("/");
//			response.addCookie(newCookie);
//			// 조회수 증가
//			result = articleService.readShareArticle(article);
//			// 쿠키가 있을 경우 (덧붙임)
//		}else if(!oldCookie.getValue().contains(visitArticle)) {
//			// 조회수 증가
//			result = articleService.readShareArticle(article);
//			oldCookie.setValue(oldCookie.getValue()+"&"+visitArticle); // 기존 쿠키에 덧붙임
//			oldCookie.setMaxAge(60*60*2);
//			oldCookie.setPath("/");
//			response.addCookie(oldCookie);
//		}
//		if(result != 0)
//			System.out.println("성공");
//	}
	
	
	
	
	// 게시글, 댓글 조회
	@RequestMapping(value = "board/share/{art_id}")
	public String detailShareArticle(	@PathVariable("art_id")
										String art_id,
										@AuthenticationPrincipal
										MemberDetails memberDetails,
										Article article,
										Integer category,
										Model model,
										HttpServletRequest request,
										HttpServletResponse response) {
		// url 데이터 변환
		article.setArt_id(Integer.parseInt(art_id));

		// 쿠키용 조회글 저장
		String visitArticle = ""+article.getArt_id()+article.getBrd_id();

		// 로그인 후 유저 정보
		MemberInfo memberInfo = null;
		if(memberDetails != null) {
			memberInfo = memberDetails.getMemberInfo();
			visitArticle += Integer.toString(memberInfo.getMem_id()); // 중복 방지 유저 id 추가 저장
			model.addAttribute("memberInfo", memberInfo);
		}
		
		
		// 조회수 로직
		Cookie oldCookie = null;
		int result = 0;
		// 쿠키 중복 체크
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				log.info("쿠키 이름 {} , 쿠키 값{}",cookie.getName(), cookie.getValue());
				if (cookie.getName().equals("visitArticle")) {
					oldCookie = cookie;
				}
			}
		}
		// 처음 방문하거나(기존 쿠키 없음), 처음 조회 하는 글일 경우 실행 
		if(oldCookie == null) {
			Cookie newCookie = new Cookie("visitArticle", visitArticle);
			newCookie.setMaxAge(60*60*2); // 쿠키 생명주기 2시간
			newCookie.setPath("/");
			response.addCookie(newCookie);
			// 조회수 증가
			result = articleService.readShareArticle(article);
			// 쿠키가 있을 경우 (덧붙임)
		}else if(!oldCookie.getValue().contains(visitArticle)) {
			// 조회수 증가
			result = articleService.readShareArticle(article);
			oldCookie.setValue(oldCookie.getValue()+"&"+visitArticle); // 기존 쿠키에 덧붙임
			oldCookie.setMaxAge(60*60*2);
			oldCookie.setPath("/");
			response.addCookie(oldCookie);
		}
		if(result != 0)
			System.out.println("성공");
		
		
		
		// 글 조회
		Article detailArticle = articleService.detailShareArticle(article);
		// 댓글 조회
		List<Reply> replyList = replyService.replyShareList(article);
		// 접속한 카테고리 이름
		String categoryName = commService.categoryName(category);
		String categoryType = commService.categoryName(article.getBrd_id());
		
		model.addAttribute("article", detailArticle);
		model.addAttribute("replyList", replyList);
		model.addAttribute("category", category);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("categoryType", categoryType);
		
		return "share/article";
	}
	
	
	
	// 글 작성 페이지 이동
	@RequestMapping(value = "board/share/write")
	public String writeForm(@AuthenticationPrincipal MemberDetails memberDetails, Article article, Model model, Integer category) {
		// 로그인 유저 정보
		if(memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		// 지역 제한 조회
		List<Region> regionList = regionService.dgRegionList();
		
		
		model.addAttribute("regionList", regionList);
		model.addAttribute("category", category);
		
		System.out.println(regionList);
		return "share/writeForm";
	}
	
	
	// 글 작성
	@PostMapping(value = "board/share/writeArticleForm")
	public String writeArticleForm(	@AuthenticationPrincipal
									MemberDetails memberDetails,
									@RequestParam("trd_enddate")
									@DateTimeFormat(pattern="yyyy-MM-dd")
									Date trd_enddate,
									Article article, 
									RedirectAttributes redirectAttributes, 
									Integer category) {
		int result = 0;
		MemberInfo memberInfo = null;
		redirectAttributes.addFlashAttribute("category", category);
		
		// 마감 날짜 저장
		article.getTrade().setTrd_enddate(trd_enddate);
		
		if(memberDetails != null) {
			memberInfo = memberDetails.getMemberInfo(); 					// 유저 정보 저장
			redirectAttributes.addFlashAttribute("memberInfo", memberInfo);	// 유저 정보 리턴
			article.setMem_id(memberInfo.getMem_id());
		}else {
			return "redirect:/board/share?category="+category;
		}
		
		log.info("제목은 나옴? [ {} ] 비었으면 안나옴", article.getArt_title());
		log.info("글쓰기 안에 있음 {}", article);
		
		// 글쓰기 실행
		result = articleService.writeShareArticle(article);
		
		
		redirectAttributes.addFlashAttribute("article", article);
		return "redirect:/board/share?category="+category;
	}
	
	// 글 추천, 비추천
	@RequestMapping(value = "board/share/vote")
	public String shareVote(@AuthenticationPrincipal
							MemberDetails memberDetails,
							Article article,
							RedirectAttributes redirectAttributes) {
		log.info("글번호 : {} 게시판 번호 : {}", article.getArt_id(), article.getBrd_id());
		return String.format("redirect:/board/share/%s?brd_id=%s&category=%s", article.getArt_id(), article.getBrd_id(), article.getBrd_id());
	}
	
	
	

	
	// 게시글 - 댓글, 대댓글 쓰기
	@PostMapping(value = "board/share/replyForm")
	public String replyForm(@AuthenticationPrincipal MemberDetails memberDetails, Reply reply, Model model, Integer category, RedirectAttributes redirectAttributes) {
		MemberInfo memberInfo = null;
		redirectAttributes.addFlashAttribute("category", category);
		
		
		if(memberDetails != null) {
			memberInfo = memberDetails.getMemberInfo(); 					// 유저 정보 저장
			redirectAttributes.addFlashAttribute("memberInfo", memberInfo);	// 유저 정보 리턴
			reply.setMem_id(memberInfo.getMem_id());
		}else {
			// 로그인 안했을때 수정필요
			return String.format("redirect:/board/share/%s?brd_id=%s&category=%s", reply.getArt_id(), reply.getBrd_id(), category);
		}
		
		System.out.println("게시판 아이디 : "+reply.getBrd_id());
		System.out.println("글번호 : "+reply.getArt_id());
		System.out.println("댓글 번호(PK) : "+reply.getRep_id());
		System.out.println("                              댓글 번호(PK)의 댓글 : "+reply.getRep_parent());
		System.out.println("내용 : "+reply.getRep_content());
		System.out.println("댓글 순서 : "+reply.getRep_step());
		
		System.out.println(reply);
		// 댓글 작성
		int result = replyService.writeReply(reply);
		
		if(result > 0)
			System.out.println("성공");
		else
			System.out.println("실패");
		return String.format("redirect:/board/share/%s?brd_id=%s&category=%s", reply.getArt_id(), reply.getBrd_id(), category);
	}
	
	
	
	// 게시글 삭제
	@RequestMapping(value = "board/share/artDelete")
	public void deleteArticle(	@AuthenticationPrincipal
									MemberDetails memberDetails,
									Article article,
									RedirectAttributes redirectAttributes) {
		log.info("글번호 : {} 게시판 번호 : {}", article.getArt_id(), article.getBrd_id());
	}
	
	
	
	// 댓글 삭제
	@RequestMapping(value = "board/share/repDelete")
	public String deleteReply(	@AuthenticationPrincipal
								MemberDetails memberDetails,
								Reply reply,
								RedirectAttributes redirectAttributes) {
		MemberInfo memberInfo = null;
		if(memberDetails != null) {
			memberInfo = memberDetails.getMemberInfo(); 					// 유저 정보 저장
			redirectAttributes.addFlashAttribute("memberInfo", memberInfo);	// 유저 정보 리턴
			reply.setMem_id(memberInfo.getMem_id());
		}else {
			System.err.println("응 로그인 안했어");
			return String.format("redirect:/board/share/%s?brd_id=%s&category=%s", reply.getArt_id(), reply.getBrd_id(), reply.getBrd_id());
		}
		
		int result = replyService.deleteReply(reply);
		
		if(result > 0)
			System.out.println("성공");
		else
			System.out.println("실패");
		
		return String.format("redirect:/board/share/%s?brd_id=%s&category=%s", reply.getArt_id(), reply.getBrd_id(), reply.getBrd_id());
	}
	
	
	
	// 댓글 수정 (Ajax)
	@ResponseBody
	@RequestMapping(value = "board/share/update")
	public List<Reply> updateReply(	@AuthenticationPrincipal
									MemberDetails memberDetails,
									Reply reply,
									RedirectAttributes redirectAttributes) {
		// 업데이트
		int result = replyService.dgUpdateReply(reply);

		// 댓글 조회
		List<Reply> replyList = null;
		if(result != 0) {
		Article article = new Article();
		article.setArt_id(reply.getArt_id());
		article.setBrd_id(reply.getBrd_id());
		replyList = replyService.replyShareList(article);
		
		log.info("리스트 {}",replyList);
		}
		return replyList;
	}
	

	 
/* 머지 후 영업 종료
	// 메인
	@RequestMapping(value = "/")
	public String indexPage() {
		return "redirect:/board/share?category=999";
	}
	// Share외의 다른 카테고리 연결
	@RequestMapping(value = "board/{categoryConnect}")
	public String togetherPage(	@PathVariable("categoryConnect")
								String 	categoryConnect, 
								Article article, 
								String 	currentPage, 
								Model 	model, 
								Integer category,
								RedirectAttributes redirectAttributes) {
		log.info("현재 {}에 접속 감지 카테고리 번호 : {}", categoryConnect, category);
		redirectAttributes.addFlashAttribute("article", article);
		redirectAttributes.addFlashAttribute("currentPage", currentPage);
		return "redirect:/board/share?category="+category;
	}
*/
}
