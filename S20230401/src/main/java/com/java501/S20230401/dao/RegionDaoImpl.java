package com.java501.S20230401.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Region;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegionDaoImpl implements RegionDao {
	private final SqlSession session;
	
	
	
	// 로그인
	@Override
	public Region getRegion(int regionCode) {
		return session.selectOne("hgGetRegion", regionCode);
	}
	@Override
	public List<Region> getSuperRegions() {
		return session.selectList("hgGetSuperRegions");
	}
	@Override
	public List<Region> getChildRegions(int parentRegionCode) {
		return session.selectList("hgGetChildRegions", parentRegionCode);
	}
}
