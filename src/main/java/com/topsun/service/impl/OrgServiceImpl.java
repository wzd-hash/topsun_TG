package com.topsun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsun.entity.BusInfo;
import com.topsun.entity.HeadCompany;
import com.topsun.mapper.OrgMapper;
import com.topsun.service.OrgService;
@Service
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgMapper orgMapper;
	


	@Override
	public List<HeadCompany> listOrgTreeRoules(Map<String, Object> map) {
		
		return orgMapper.listOrgTreeRoules(map);
	}

	@Override
	public List<BusInfo> listOrgTreeBus(Map<String, Object> map) {
		
		return orgMapper.listOrgTreeBus(map);
	}



	
}
