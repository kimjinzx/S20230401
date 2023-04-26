package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Region;

public interface RegionDao {
	
	// 유현규
	public Region getRegion(int regionCode);
	public List<Region> getSuperRegions();
	public List<Region> getChildRegions(int parentRegionCode);
	
	
	// 임동빈
	List<Region> regionName();
	List<Region> parentRegionName();

}
