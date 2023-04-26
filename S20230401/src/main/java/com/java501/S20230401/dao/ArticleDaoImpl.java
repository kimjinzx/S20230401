package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.security.o3logon.a;

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
			result = session.insert("dgWriteShareArticle",article);
			if(result>0) System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	// 유현규
	@Override
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType) {
		List<ArticleMember> articleList = null;
		articleList = session.selectList("hgGetSummary" + summaryType.toString(), boardNum);
		for (ArticleMember am : articleList) {
			try { am.setHis_good(session.selectOne("hgCalculateGood", am)); }
			catch(Exception e) { am.setHis_good(0); }
			try { am.setHis_normal(session.selectOne("hgCalculateNormal", am)); }
			catch(Exception e) { am.setHis_normal(0); }
			try { am.setHis_bad(session.selectOne("hgCalculateBad", am)); }
			catch(Exception e) { am.setHis_bad(0); }
			if (am.getTrd_id() != 0) {
				try {
					am.setTrd_enddate(session.selectOne("hgCalculateEnddate", am));
				} catch(Exception e) {
					
				}
			}
			try { am.setRep_count(session.selectOne("hgCountReply", am)); }
			catch(Exception e) { am.setRep_count(0); }
		}
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
	public List<Article> listArticle(Article article) {
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
}
