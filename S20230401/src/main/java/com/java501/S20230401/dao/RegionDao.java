package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Region;

@Repository
public interface RegionDao {

	List<Region> regionName();

}
