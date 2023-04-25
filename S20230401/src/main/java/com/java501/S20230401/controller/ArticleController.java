package com.java501.S20230401.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.MemberInfo;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.ReplyMember;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.CommService;
import com.java501.S20230401.service.RegionService;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService as;
	private final RegionService rs;
	private final CommService cs;
	private final ReplyService reps;
	
	private Integer getBoardIdByBoardName(String boardName) {
		switch(boardName) {
			case "together": return 1000;
			case "dutchpay": return 1100;
			case "share": return 1200;
			case "community": return 1300;
			case "information": return 1400;
			case "customer": return 1500;
			default: return null;
		}
	}
	
	@GetMapping(value = "/board/{boardName}/write")
	public String writeArticle(@PathVariable String boardName, @RequestParam(required = false) Integer brd_id,
							   @RequestParam(required = false) Integer pageNum,
							   @AuthenticationPrincipal MemberDetails memberDetails,
							   Model model) {
		String[] trades = { "together", "dutchpay", "share" };
		boolean isTradeBoard = Arrays.stream(trades).anyMatch(boardName::equals);
		if (isTradeBoard) {
			Map<Region, List<Region>> regionHierachy = new HashMap<Region, List<Region>>();
			List<Region> superRegions = rs.getSuperRegions();
			for (Region sups : superRegions) regionHierachy.put(sups, rs.getChildRegions(sups.getReg_id()));
			model.addAttribute("superRegions", superRegions);
			model.addAttribute("regions", regionHierachy);
		}
		model.addAttribute("isTradeBoard", isTradeBoard);
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		model.addAttribute("boardName", boardName);
		int boardId = brd_id == null ? getBoardIdByBoardName(boardName) : brd_id;
		model.addAttribute("brd_id", boardId);
		if (pageNum != null) model.addAttribute("pageNum", pageNum);
		
		List<Comm> categoryList = cs.getCategoryListBySuper(getBoardIdByBoardName(boardName));
		model.addAttribute("categories", categoryList);
		
		return "writeArticleForm";
	}
	
	@ResponseBody
	@PostMapping(value = "/board/{boardName}/imageUpload")
	public void imageUploadInArticle(@PathVariable String boardName, HttpServletResponse response,
									 MultipartHttpServletRequest request) throws Exception {
		JSONObject jsonObject = null;
		PrintWriter printWriter = null;
		//MultipartFile file = request.getFile("upload");
		MultipartFile file = request.getFile("uploadFile");
		OutputStream out = null;
		if (file != null) {
			if (file.getSize() > 0 && !file.getName().isBlank() && !file.getName().isEmpty()) {
				//System.out.println(file.getContentType());
				if (file.getContentType().toLowerCase().startsWith("image/")) {
					try {
						String fileName = file.getOriginalFilename();
						byte[] bytes = file.getBytes();
						String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/article");
						//System.out.println(uploadPath);
						File uploadFile = new File(uploadPath);
						if (!uploadFile.exists()) {
							uploadFile.mkdir();
						}
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu_MM_dd_HH_mm_ss");
						String uuid = UUID.randomUUID().toString();
						String dateStr = dtf.format(LocalDateTime.now());
						uploadPath += "/" + uuid + "_" + dateStr + "_" + fileName;
						out = new FileOutputStream(new File(uploadPath));
						out.write(bytes);
						String fileUrl = request.getContextPath() + "/uploads/article/" + uuid + "_" + dateStr + "_" + fileName;
						//System.out.println(fileUrl);
						jsonObject = new JSONObject();
						jsonObject.append("uploaded", 1);
						jsonObject.append("fileName", fileName);
						jsonObject.append("url", fileUrl);
						//jsonObject.append("url", uploadPath);
						printWriter = response.getWriter();
						printWriter.println(jsonObject.toString());
						printWriter.flush();
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						if (printWriter != null) printWriter.close();
					}
				}
			}
		}
	}

	@PostMapping(value = "/board/{boardName}/writeProc")
	public String writeArticleProcess(Article article, @RequestParam int category,
									  @PathVariable String boardName,
									  String articleEditor,
									  @AuthenticationPrincipal MemberDetails memberDetails) {
		int result = as.insertArticle(article);
		return "redirect:/board/" + boardName + "?brd_id=" + category;
	}
	
	@GetMapping(value = "/board/{boardName}/{art_id}")
	public String viewArticle(@AuthenticationPrincipal MemberDetails memberDetails,
							  @PathVariable String boardName,
							  @PathVariable int art_id, @RequestParam int brd_id,
							  @RequestParam Integer category,
							  String currentPage,
							  Model model) {
		if (memberDetails != null) model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		Article searcher = new Article();
		searcher.setArt_id(art_id);
		searcher.setBrd_id(brd_id);
		ArticleMember article = as.getArticleMemberById(searcher);
		MemberInfo writerInfo = as.getMemberInfoById(article.getMem_id());
		/*
		 * List<Comm> categories = new ArrayList<Comm>();
		 * for (int i = 1000; i <= 1500 ; i += 100) categories.addAll(cs.getCategoryListBySuper(i));
		 * Map<Integer, String> categoryMap = categories.stream().collect(Collectors.toMap(Comm::getComm_id, Comm::getComm_value)); model.addAttribute("categories", categoryMap);
		 */
		if (category.intValue() != brd_id) model.addAttribute("board", cs.getCommById(brd_id).getComm_value());
		else model.addAttribute("board", null);
		String boardScope = cs.getValueById(category);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("boardScope", boardScope);
		model.addAttribute("boardName", boardName);
		model.addAttribute("writerInfo", writerInfo);
		model.addAttribute("article", article);
		model.addAttribute("brd_id", brd_id);
		return "viewArticle_Backup";
	}
	
	@PostMapping(value = "/board/{boardName}/{art_id}/replies")
	public String viewReply(@PathVariable String boardName,
						    @PathVariable int art_id,
						    @RequestBody Map<String, Object> data,
						    Model model) {
		int brd_id = (int)data.get("brd_id");
		Article article = new Article();
		article.setArt_id(art_id);
		article.setBrd_id(brd_id);
		List<ReplyMember> replies = reps.getReplyByArticle(article);
		model.addAttribute("replies", replies);
		return "replyMiddleView";
	}
}
