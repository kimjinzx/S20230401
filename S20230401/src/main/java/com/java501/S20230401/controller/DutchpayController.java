package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.service.ArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 같이사요 페이지 계열 컨트롤러 : 김진현
@Controller
@RequiredArgsConstructor
@Slf4j
public class DutchpayController {
	private final ArticleService as;
	
	@RequestMapping(value = "/board/dutchpay") // 대분류 같이사요 + 중분류 하위카테고리 이동 및 List조회  
	public String dutchpayList(Article_Trade_Reply atr,int category, Model model) {
		String viewName;
		 String boardName;
	      switch(category) {
	      case 1110: viewName = "dutchpayFoodList"; boardName = "FoodList"; break;
	      case 1120: viewName = "dutchpayClothesList"; boardName = "ClothesList"; break;
	      case 1130: viewName = "dutchpayHouseStuffList"; boardName = "HouseStuffList"; break;
	      case 1140: viewName = "dutchpayOverseasList"; boardName = "OverseasList"; break;
	      case 1150: viewName = "dutchpayEtcList"; boardName = "EtcList"; break;
	      default: viewName = "dutchpayList"; boardName = "List"; break;
	      }
	      List<Article_Trade_Reply> dutchpayList = as.getDutchpayList(boardName);
	      System.out.println("controller dutchpayList size() -> "+dutchpayList.size());
	      model.addAttribute("dutchpayList", dutchpayList);
	      
	      
	      return "dutchpay/" + viewName;
	}
	
	@RequestMapping(value ="/dutchpay/dutchpayDetail") //상세 게시글    
	public String dutchpayDetail(Article_Trade_Reply atr ,Model model) {
		System.out.println("controller detail brd_id -> "+atr.getBrd_id());
		System.out.println("controller detail art_id -> "+atr.getArt_id());
		Article_Trade_Reply detail = as.detail1(atr);
		model.addAttribute("detail", detail);
		
		return "/dutchpay/dutchpayDetail";
		
	}

	@RequestMapping(value = "dutchpay/dutchpayWriteForm") //글쓰기 폼 
	public String dutchpayWriteForm(Model model) {
		System.out.println("dutchpay/dutchpayWriteForm start..");
		
		List<Comm> category = as.category1();
		for (Comm c : category) System.out.println(c.getComm_id() + " : " + c.getComm_value());
		System.out.println("dutchpay/dutchpayWriteForm category.size()- >"+category.size());
		List<Region> loc = as.loc1();
		//for (Region l : loc) System.out.println(l.getReg_id() + " : " + l.getReg_name());
		System.out.println("dutchpay/dutchpayWriteForm loc.size() ->"+loc.size());
	
		model.addAttribute("categories", category);
		model.addAttribute("loc", loc);
		
		return "dutchpay/dutchpayWriteForm";
	}
	
	

	@RequestMapping(value = "dutchpay/dutchpayUpdateForm") //업데이트(수정) 폼 + 드롭다운 
	public String dutchpayUpdateForm(Article_Trade_Reply atr, Model model) {
		System.out.println("dutchpay/dutchpayUpdateForm start..");
		System.out.println("controller updateForm brd_id  -> "+atr.getBrd_id());
		System.out.println("controller updateForm art_id  -> "+atr.getArt_id());
		Article_Trade_Reply updateForm = as.updateForm1(atr);
		model.addAttribute("updateForm", updateForm);
		
		List<Region> loc_ud = as.loc_ud1();
		//for (Region l : loc_ud) System.out.println(l.getReg_id() + " : " + l.getReg_name());
		System.out.println("dutchpay/dutchpayUpdateForm loc_ud.size()- >"+loc_ud.size());
		
		model.addAttribute("loc_ud", loc_ud);
		
		return "dutchpay/dutchpayUpdateForm";
	}
	
	@PostMapping(value = "dutchpay/dutchpayWritePro") // 글내용 삽입 (insert) 
	public String insert(Article_Trade_Reply atr ,RedirectAttributes ra) {
		
		System.out.println("start insert button");
		System.out.println(atr);
		System.out.println("controller insert brd_id  -> "+atr.getBrd_id());
		as.dutchpayInsert1(atr);
		ra.addFlashAttribute("atr", atr);  //model.addAttribute와 다른점은 컨트롤러 내에서 매핑할 시 이렇게 사용하는게 좋음
		int brd_id = atr.getBrd_id(); //확인 버튼 누르면 드롭다운(카테고리) 에서 고른 해당카테고리로 이동 
		return "redirect:/board/dutchpay?category="+brd_id;
	}
	
	//detail에서 쓰던 brd_id,atr_id,trd_id들을 가져온 updateForm에서 그것들을 사용해 update
	@PostMapping(value = "dutchpay/dutchpayUpdatePro") //글내용 수정(update)
	public String update(Article_Trade_Reply atr, RedirectAttributes ra) {
		
		System.out.println("start update button");
		System.out.println(atr);
		System.out.println("controller update brd_id -> "+atr.getBrd_id());
		System.out.println("controller update art_id -> "+atr.getArt_id());
		System.out.println("controller update trd_id -> "+atr.getTrd_id());
		as.dutchpayUpdate1(atr);
		ra.addFlashAttribute("atr", atr);  
		int brd_id = atr.getBrd_id();
		return "redirect:/board/dutchpay?category="+brd_id;
	}
	
	@PostMapping(value = "/dutchpay/dutchpayDelete") //게시글 삭제
	public String delete(Article_Trade_Reply atr, RedirectAttributes ra) {
		
		System.out.println("start delete button");
		System.out.println("controller delete brd_id -> "+atr.getBrd_id());
		System.out.println("controller delete isdelete -> "+atr.getIsdelete());
		as.dutchpayDelete1(atr);
		ra.addFlashAttribute("atr",atr);
		int brd_id = atr.getBrd_id();
		return "redirect:/board/dutchpay?category="+brd_id;
	}


}

		
