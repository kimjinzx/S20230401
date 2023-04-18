package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Region;

public interface RegionService {
	public Region getRegion(int regionCode);
	public List<Region> getSuperRegions();
	public List<Region> getChildRegions(int parentRegionCode);
}
