package com.topsun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsun.mapper.ExportMapper;
import com.topsun.model.OperationModel;
import com.topsun.service.ExportService;
@Service
public class ExportServiceImpl implements ExportService{

	@Autowired
	private ExportMapper exportMapper;
	

	@Override
	public List<OperationModel> listOperation(Map<String, Object> map) {
		
		return exportMapper.listOperation(map);
	}

}
