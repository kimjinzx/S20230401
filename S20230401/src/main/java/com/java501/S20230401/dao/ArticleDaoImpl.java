package com.java501.S20230401.dao;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.model.Article_Trade_Reply;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.MemberInfo;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleDaoImpl implements ArticleDao {
	private final SqlSession session;
	
	
	// 양동균
	@Override
	public int allTotalArt(Article article) {
		int allArtCnt = 0;
		try {
			allArtCnt = session.selectOne("dgAllArtCnt", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allArtCnt;
	}
	// 모든 글 조회
	@Override
	public List<Article> allArticleList(Article article) {
		List<Article> allArticleList = null;
		try {
			allArticleList = session.selectList("dgAllArticleList", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allArticleList;
	}
	// 게시글 조회 (art_id, brd_id)
	@Override
	public Article detailShareArticle(Article article) {
		Article detailArticle = null;
		try {
			detailArticle = session.selectOne("dgDetailShareArticle",article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailArticle;
	}
	// 게시글 조회수 증가
	@Override
	public int readShareArticle(Article article) {
		int result = 0;
		try {
			result = session.update("dgReadShareArticle", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// Share 글쓰기 프로시저
	@Override
	public int writeShareArticle(Article article) {
		int result = 0;
		
		try {
//			// Not Null
//			Class<?> clazz = article.getClass();
//			Field[] fields = clazz.getDeclaredFields();
//			for(Field field : fields) {
//				// private 필드 접근
//				field.setAccessible(true);
//				// Null값인 필드 중에 Not Null 요소
//				if(field.get(article) == null && Arrays.asList("art_good","art_bad","art_read","art_isnotice","isdelete").contains(field.getName())) {
//					field.set(article, 0);
//					log.info("Null 값인 필드 : {} 0으로 저장 후 값 확인 : {}",field.getName(), field.get(article));
//				}
//			}
			result = session.insert("dgWriteShareArticle",article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 글 삭제
	@Override
	public int dgDeleteArticle(Article article) {
		int result = 0;
		try {
			result = session.update("dgDeleteArticle", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 글 추천
	@Override
	public int dgVoteGood(Article article) {
		int result = 0;
		try {
			result = session.update("dgVoteGood", article);
			if(result != 0) result = session.selectOne("dgArtGood", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 글 비추천
	@Override
	public int dgVoteBad(Article article) {
		int result = 0;
		try {
			result = session.update("dgVoteBad", article);
			if(result != 0) result = session.selectOne("dgArtBad", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 게시글 수정
	@Override
	public int updateShare(Article article) {
		int result = 0;
		try {
			result = session.update("dgUpdateArticle", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	// 유현규
	@Override
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType) {
		List<ArticleMember> articleList = null;
//		articleList = session.selectList("hgGetSummary" + summaryType.toString(), boardNum);
//		for (ArticleMember am : articleList) {
//			try { am.setHis_good(session.selectOne("hgCalculateGood", am)); }
//			catch(Exception e) { am.setHis_good(0); }
//			try { am.setHis_normal(session.selectOne("hgCalculateNormal", am)); }
//			catch(Exception e) { am.setHis_normal(0); }
//			try { am.setHis_bad(session.selectOne("hgCalculateBad", am)); }
//			catch(Exception e) { am.setHis_bad(0); }
//			if (am.getTrd_id() != 0) {
//				try {
//					am.setTrd_enddate(session.selectOne("hgCalculateEnddate", am));
//				} catch(Exception e) {
//					
//				}
//			}
//			try { am.setRep_count(session.selectOne("hgCountReply", am)); }
//			catch(Exception e) { am.setRep_count(0); }
//		}
		articleList = session.selectList("hgGetSummaryOnce" + summaryType.toString(), boardNum);
		return articleList;
	}
	
	@Override
	public int insertArticle(Article article) {
		return session.insert("hgInsertArticle", article);
	}
	
	@Override
	public Article getArticleById(Article searcher) {
		return session.selectOne("hgGetArticleById", searcher);
	}
	
	@Override
	public MemberInfo getMemberInfoById(int mem_id) {
		return session.selectOne("hgGetMemberInfoById", mem_id);
	}
	
	@Override
	public ArticleMember getArticleMemberById(Article searcher) {
		return session.selectOne("hgGetArticleMemberById", searcher);
	}
	
	
	
	
	
	
	
	
	// 백준
	@Override
	public Integer totalArticle(int brd_id) {
		int totArticleCount = 0;
		try {
			if (brd_id % 100 == 0) totArticleCount = session.selectOne("bjarticleIndex", brd_id);
			else totArticleCount = session.selectOne("bjarticlePart", brd_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return totArticleCount;
	}

	@Override
	public List<Article> articleTotal(Article article) {
		List<Article> articleList = null;
		
		try {
			articleList = session.selectList("bjarticleIndexAll",article);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return articleList;
	}

	@Override
	public Article detailContent(Article article) {
		Article detailCon = new Article();
		
		try {
			detailCon = session.selectOne("bjdetailContent",article);
		} catch (Exception e) {
			System.out.println("에러"+e.getMessage());
		}
		return detailCon;
	}
	
	@Override
	public List<Article> artcleMenu(Article article) {
		List<Article> articleMenu = null;
		try {
			articleMenu = session.selectList("bjArticleMenu",article);
		} catch (Exception e) {
			System.out.println("게시판 메뉴에러"+e.getMessage());
		}
		return articleMenu;
	}

	@Override
	public Integer replyCount(int art_id) {
		int countReply = 0;
		try {
			countReply = session.selectOne("bjCountReply", art_id);
		} catch (Exception e) {
			System.out.println("리플 에러"+e.getMessage());
		}
		
		return countReply;
	}

	@Override
	public Integer upreadCount(Article article) {
		System.out.println("아티클 조회수 다오임플 시작");
		Article countUpread = article;
		System.out.println("아티칼 조회수 카운트업리드"+countUpread);
		Integer count = 0;
		try {
			count = session.update("bjreadCount",countUpread);
			System.out.println("업데이트 결과 "+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Article> listMagnager() {
		List<Article> bjwrite = null;
		try {
			bjwrite = session.selectList("bjWriteArticle");
		} catch (Exception e) {
			System.out.println("에러->"+e.getMessage());
		}
		return bjwrite;
	}

	@Override
	public int bjWriteArticle(Article article) {
		System.out.println("아티클다오임플 라이트아티클 시작");
		int writeArticle = 0;
		
		try {
			writeArticle = session.insert("bjWriteArticle", article);
			System.out.println("아티클 다오 임플 라이트아티클1"+ writeArticle);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("아티클 다오 임플 라이트아티클2"+ writeArticle);
		return writeArticle;
	}
	
	@Override
	public int bjUpdateArticle(Article article) {
		int update = 0;
		try {
			update = session.update("bjUpdate",article);
		} catch (Exception e) {
			System.out.println("업데이트에러"+e.getMessage());
		}
		System.out.println("업데이트 아티클값->"+article);
		return update;
	}

	@Override
	public int delete(Article article) {
		int delResult = 0;
		try {
			delResult = session.update("bjDelete",article);
		} catch (Exception e) {
			System.out.println("삭제대기에러"+e.getMessage());
		}
		return delResult;
	}
	
	
	
	
	
	
	
	
	// 임동빈
	@Override
	public int totalArticle(Article article) {
		int totArticleCount = 0;
		System.out.println("ArticleDaoImple Start total...");

		try {
			if (article.getBrd_id() == 1000) {
				totArticleCount = session.selectOne("ArticleTotalCnt");
			} else {
				totArticleCount = session.selectOne("ArticleBoardCnt", article);
			}
			System.out.println("ArticleDaoImpl totalArticle totArticleCount-> " + totArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl totalArticle Exception-> " + e.getMessage());
		}
		return totArticleCount;
	}

	@Override
	public List<Article> dbListArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleDaoImpl listArticle Start...");
		try {
			// article.getBrd_id() 따라서 분기 --> 전체
			if (article.getBrd_id() == 1000) {
				articleList = session.selectList("tkArticleListAll", article);
			} else {
				// 1010 밥/카페
				// 1020 스포츠/운동
				// 1030 쇼핑
				// 1040 문화 생활
				// 1050 취미 생활
				// 1060 기타
				articleList = session.selectList("tkArticleListBoard", article);
			}
			System.out.println("ArticleDaoImpl listArticle ArticleList.size()-> " + articleList.size());
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl listArticle e.getMessage()-> " + e.getMessage());
		}
		return articleList;
	}
	@Override
	public Article detailArticle(Article article) {
		Article detailArticle = null;
		System.out.println("ArticleDaoImpl detailArticle Start...");
		try {
			detailArticle = session.selectOne("tkArticleDetail", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl detailArticle e.getMessage()->" + e.getMessage());
		}
		return detailArticle;
	}
	@Override
	public List<Article> replyList(Article article) {
		List<Article> replyList = null;
		try {
			replyList = session.selectList("tkReplyList", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl replyList => " + e.getMessage());
		}
		return replyList;
	}

	@Override
	public void dbWriteArticle(Article article) {
		System.out.println("ArticleDaoImpl wirteArticle start...");
		try {
			if (article.getReg_id2() == null) {
				article.setReg_id(article.getReg_id1());
			} else {
				article.setReg_id(article.getReg_id2());
			}
			session.selectOne("insertArticle", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl insertArticle => " + e.getMessage());
		}
	}
	@Override
	public int deleteArticle(Article article) {
		int deleteArticle = 0;

		try {
			deleteArticle = session.update("deleteArticle", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl deleteArticle => " + e.getMessage());
		}
		return deleteArticle;
	}
	@Override
	public void dbUpdateArticle(Article article) {
		System.out.println("ArticleDaoImpl updateArticle start...");
		try {
			if (article.getReg_id2() == null) {
				article.setReg_id(article.getReg_id1());
			} else {
				article.setReg_id(article.getReg_id2());
			}
			session.selectOne("updateArticle", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl updateArticle => " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	// 김진현
	@Override
	public List<Article_Trade_Reply> getDutchpayList(String boardName) {
		List<Article_Trade_Reply> dutchpayList2 = null;
		try {
			dutchpayList2 = session.selectList("JHDutchpay" + boardName);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl dutchpayList2 Exception -> "+e.getMessage());
		}
		return dutchpayList2;
	}
	@Override
	public Article_Trade_Reply detail2(Article_Trade_Reply atr) {
		Article_Trade_Reply atr2 = null;
		try {				
			atr2 = session.selectOne("JHDutchpayDetail",atr);
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl detail2 Exception -> "+e.getMessage());
		}
		return atr2;
	}
	@Override
	public void dutchpayInsert2(Article_Trade_Reply atr) {
		try {
			System.out.println(atr);
			session.selectOne("JHInsert",atr);
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl dutchpayInsert2 Exception -> "+e.getMessage());
		}
	}
	@Override
	public List<Comm> category2() {
		List<Comm> cm = null;
		try {
			cm = session.selectList("JHCategory");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl category2 Exception -> "+e.getMessage());
		}
		return cm;
	}
	@Override
	public List<Region> loc2() {
		List<Region> re = null;
		try {
			re = session.selectList("JHLoc");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl region2 Exception -> "+e.getMessage());
		}
		return re;
	}
	@Override
	public Article_Trade_Reply updateForm2(Article_Trade_Reply atr) {
		Article_Trade_Reply updateForm = null;
		try {
			updateForm = session.selectOne("JHUpdateForm",atr);
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl updateForm2 Exception -> "+e.getMessage());
		}
		return updateForm;
	}
	@Override
	public List<Comm> category_ud2() {
		List<Comm> cm = null;
		try {
			cm = session.selectList("JHCategoryUd");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl category_ud2 Exception -> "+e.getMessage());
		}
		return cm;
	}
	@Override
	public List<Region> loc_ud2() {
		List<Region> re = null;
		try {
			re = session.selectList("JHLocUd");
		} catch (Exception e) {
			System.out.println("ArticleServiceImpl loc_ud2 Exception -> "+e.getMessage());
		}
		return re;
	}
	
	
	
	
	
	
	
	
	
	// 김찬영
	// 총리스트
	@Override
	public int totalArticle() {
		int totArticleCount = 0;
		System.out.println("ArticleImpl Start total...");
		
		try {
			totArticleCount = session.selectOne("ArticleTotal");
			System.out.println("ArticleImpl totalArticle totArticleCount->" + totArticleCount);
		} catch (Exception e) {
			System.out.println("ArticleImpl totalArticle Exception->"+e.getMessage());
		}
		
		return totArticleCount;
	}
	// 리스트조회
	@Override
	public List<Article> listArticle(Article article) {
		List<Article> articleList = null;
		System.out.println("ArticleDaoImpl listArticle Start...");
		try {
			articleList = session.selectList("cyArticleListAll", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl listArticle e.getMessage()->"+e.getMessage());
		}
		return articleList;
	}
	//상세페이지
	@Override
	public Article cyArticlereadDetail(Article article) {
		System.out.println("ArticleDaoImpl article Start...");
		Article result = null;
		try {
			result = session.selectOne("cyArticlereadDetail", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	// 상세페이지
	@Override
	public Article detatilArticle(int art_title) {
		System.out.println("ArticleDaoImpl detail start..");
		Article article = new Article();
		
		try {
			article = session.selectOne("cyArticleSelOne", art_title);
			System.out.println("ArticleImpl detail brd_id->" +article.getBrd_id());
		} catch (Exception e) {
			System.out.println("articleDaoImpl detail Excpetion->"+e.getMessage());
		}
		
		return article;
	}
	//상세페이지 수정
	@Override
	public Article cyArticlereadupdate(Article article) {
		System.out.println("ArticleDaoImpl article Start...");
		Article result = null;
		try {
			result = session.selectOne("cyArticlereadupdate", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	// 게시물 작성
	@Override
	public int cyArticleinsert(Article article) {
		System.out.println("ArticleDaoImpl insert Start...");
		int result = 0;
		try {
			result = session.insert("cyArticleinsert", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	//게시물 수정
	@Override
	public int cyArticlemodify(Article article) {
		System.out.println(article);
		System.out.println("ArticleDaoImpl modify Start...");
		int result = 0;
		try {
			result = session.update("cyArticlemodify", article);
		} catch (Exception e) {
			System.out.println("ArticleImpl article e.getMessage()->"+e.getMessage());
		}
		return result;
	}
	
	
	
	
	
	// 최승환
	@Override
	public int totalCustomer() {
		int totCustomerCount = 0;
		System.out.println("ArticleDaoImpl Start totalCustomer...");
		
		try {
			totCustomerCount = session.selectOne("shCustomerCount");
			System.out.println("ArticleDaoImpl shCustomerCount totCustomerCount->" +totCustomerCount);
			
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl shCustomerCount Exception->"+e.getMessage());
		}
		return totCustomerCount;
	}
	
	@Override
	public List<Article> listCustomer(Article article) {
		List<Article> customerList = null;
		System.out.println("ArticleDaoImpl listCustomer Start ..." );
		try {
			//                             Map ID        parameter
			customerList = session.selectList("shListCustomer", article);
			System.out.println("ArticleDaoImpl listCustomer customerList.size()->"+customerList.size());
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl listCustomer e.getMessage()->"+e.getMessage());
		}
		return customerList;	
	}
	
	@Override
	public Article detailCustomer(Article article) {
		System.out.println("ArticleDaoImpl detailCustomer start...");
		Article customerDetail = new Article();
		try {
			customerDetail = session.selectOne("shCustomerDetail", article);
		
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl detail Exception->"+e.getMessage());
		}
		return customerDetail;
	}

	@Override
	public List<Article> listCustomerMenu(Article article) {
		List<Article> listMenu = null;
		try {
			listMenu = session.selectList("shListCustomerMenu", article);
			System.out.println("다오 리스트메뉴"+listMenu);
		} catch (Exception e) {
			System.out.println("메뉴에러"+e.getMessage());
		}
			
		return listMenu;
	}
	@Override
	public int insertCustomer(Article article) {
		int result = 0;
		System.out.println("ArticleDaoImpl insertCustomer start...");
		try {
			result = session.insert("shInsertCustomer", article);
		} catch (Exception e) {
			System.out.println("ArticleDaoImpl insertCustomer Exception->"+e.getMessage());
		}
		return result;
	}





}
