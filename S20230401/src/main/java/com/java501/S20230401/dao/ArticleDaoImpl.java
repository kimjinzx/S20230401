package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
//	@Override
//	public int totalArt(Article article) {
//		int artCnt = 0;
//		try {
//			artCnt = session.selectOne("dgArtCnt", article);
//			log.info("Article 게시판 카운트 : {}", artCnt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return artCnt;
//	}
//
//	@Override
//	public List<Article> articleList(Article article) {
//		List<Article> articleList = null;
//		try {
//			articleList = session.selectList("dgArticleList", article);
//			log.info("Article {} 게시판 조회", articleList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return articleList;
//	}

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
	
	
	
	// 현규
	
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
	public int writeArticle(Article article) {
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
	public int updateArticle(Article article) {
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
}
