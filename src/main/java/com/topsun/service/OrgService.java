package com.topsun.service;

import java.util.List;
import java.util.Map;

import com.topsun.entity.BusInfo;
import com.topsun.entity.HeadCompany;

public interface OrgService {

//	//组织树中的线路
	List<HeadCompany> listOrgTreeRoules(Map<String, Object> map);
//	//组织树中的车辆
	List<BusInfo> listOrgTreeBus(Map<String, Object> map);

}
