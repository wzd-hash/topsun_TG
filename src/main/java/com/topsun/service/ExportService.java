package com.topsun.service;

import java.util.List;
import java.util.Map;

import com.topsun.model.OperationModel;

public interface ExportService {

	//运维统计
	List<OperationModel> listOperation(Map<String, Object> map);

}
