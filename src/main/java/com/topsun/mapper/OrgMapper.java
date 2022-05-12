package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.topsun.entity.BusInfo;
import com.topsun.entity.HeadCompany;

@Mapper
public interface OrgMapper {
	
//	//组织树中的线路
	List<HeadCompany> listOrgTreeRoules(Map<String, Object> map);
	//组织树中的车辆
	List<BusInfo> listOrgTreeBus(Map<String, Object> map);
}
