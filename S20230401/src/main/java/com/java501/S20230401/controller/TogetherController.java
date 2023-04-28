package com.java501.S20230401.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;

// 함께해요 페이지 계열 컨트롤러 : 임동빈
@Controller
@RequiredArgsConstructor
public class TogetherController {

	private final ArticleService as;
	private final ReplyService rs;

	@RequestMapping(value = "/board/together")
	public String articleList(@AuthenticationPrincipal MemberDetails memberDetails,  // 세션의 로그인 유저 정보
							  Article article,
							  int category,
							  String currentPage,
							  Model model) {
		
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if (memberDetails != null) 
				model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		// category 값을 brd_id에 할당.
		article.setBrd_id(category);
		int number = article.getBrd_id();

		// 전체 게시글 개수 Count
		int totalArticle = as.dbtotalArticle(article);

		// Paging 작업
		Paging page = new Paging(totalArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());

		// 게시글 리스트 작업
		List<Article> listArticle = as.dbListArticle(article);

		model.addAttribute("article", article);
		model.addAttribute("totalArticle", totalArticle);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("category", number);
		model.addAttribute("page", page);

		return "together/listArticle";
	}

	@RequestMapping(value = "/board/detailArticle")
	public String detailArticle(Article article,
								Model model) {
		System.out.println("ArticleController Start detailArticle...");

		// 상세게시글 요소 구현
		Article detailArticle = as.dbdetailArticle(article);
		model.addAttribute("detailArticle", detailArticle);

		// 게시글 별 댓글 리스트
		List<Article> replyList = as.dbreplyList(article);
		model.addAttribute("replyList", replyList);

		return "together/detailArticle";
	}

	@RequestMapping(value = "/board/writeFormArticle")
	public String writeFormArticle(@AuthenticationPrincipal MemberDetails memberDetails,
								   Model model) {
		if (memberDetails != null) model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		System.out.println("ArticleController Start writeFormArticle...");

		// 카테고리별 콤보박스
		List<Comm> categoryList = as.categoryName();
		model.addAttribute("categories", categoryList);

		// 성별 콤보박스
		List<Comm> genderList = as.genderName();
		model.addAttribute("genders", genderList);

		// 지역별 콤보박스
		List<Region> regionList = as.regionName();
		model.addAttribute("regions", regionList);

		// 지역별 부모 콤보박스
		List<Region> parentRegionList = as.parentRegionName();
		model.addAttribute("parentRegions", parentRegionList);

		return "together/writeFormArticle";
	}

	@RequestMapping(value = "/board/writeArticle")
	public String writeArticle(Article article,
							   Model model, 
							   @RequestParam("trd_enddate1")
							   @DateTimeFormat(pattern = "yyyy-MM-dd") Date trd_enddate) {
		article.setTrd_enddate(trd_enddate);
		
		// 프로시저 Insert_Article 이용 => 게시글 작성
		as.dbWriteArticle(article);
		int insertResult = article.getInsert_result();
		int brd_id = article.getBrd_id();

		model.addAttribute("insertResult", insertResult);
		model.addAttribute("article", article);

		if (insertResult > 0) {
			return "redirect:/board/together?category=" + brd_id;
		} else {
			model.addAttribute("msg", "입력실패");
			return "forward:/board/writeFormArticle";
		}

	}

	@RequestMapping(value = "/board/deleteArticle")
	public String deleteArticle(Article article,
								Model model) {

		// 게시글 삭제 (isdelete = 0 => 1)
		int deleteresult = as.dbdeleteArticle(article);
		model.addAttribute("result", deleteresult);
		
		return "redirect:/board/together?category=1000";
	}

	@RequestMapping(value = "/board/updateFormArticle")
	public String updateFormArticle(Article article, Model model) {

		// 게시글 수정 양식 (상세 게시글 값 가져오기)
		Article updateFormArticle = as.dbdetailArticle(article);
		model.addAttribute("article", updateFormArticle);

		// 카테고리별 콤보박스
		List<Comm> categoryList = as.categoryName();
		model.addAttribute("categories", categoryList);

		// 성별 콤보박스
		List<Comm> genderList = as.genderName();
		model.addAttribute("genders", genderList);

		// 지역별 콤보박스
		List<Region> regionList = as.regionName();
		model.addAttribute("regions", regionList);

		// 지역별 부모 콤보박스
		List<Region> parentRegionList = as.parentRegionName();
		model.addAttribute("parentRegions", parentRegionList);

		return "together/updateFormArticle";
	}

	@RequestMapping(value = "/board/updateArticle")
	public String updateArticle(Article article,
								Model model,
								@RequestParam("trd_enddate1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date trd_enddate) {

		article.setTrd_enddate(trd_enddate);
		// 게시글 수정 (프로시저 사용 => Update_Article)
		as.dbUpdateArticle(article);
		int updateArticle = article.getInsert_result();
		int brd_id = article.getBrd_id();

		model.addAttribute("article", article);

		if (updateArticle > 0) {
			return "redirect:/board/together?category=" + brd_id;
		} else {
			model.addAttribute("msg", "입력실패");
			return "forward:/board/updateFormArticle";
		}
	}
	
	@RequestMapping(value= "/board/insertReply")
	public String insertReply(Reply reply, Model model) {
		int insertReply = rs.dbInsertReply(reply);
		
		int art_id = reply.getArt_id();
		int brd_id = reply.getBrd_id();
		
		System.out.println("rep_content = " + reply.getRep_content());
		model.addAttribute("insertReply", insertReply);
		
		return "redirect:/board/detailArticle?art_id="+art_id+"&brd_id="+brd_id;
	
	}
	
	
}