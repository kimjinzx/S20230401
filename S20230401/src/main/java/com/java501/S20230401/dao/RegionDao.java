package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Region;

public interface RegionDao {
	public Region getRegion(int regionCode);
	public List<Region> getSuperRegions();
	public List<Region> getChildRegions(int parentRegionCode);
	List<Region> regionName();
	List<Region> parentRegionName();

}
