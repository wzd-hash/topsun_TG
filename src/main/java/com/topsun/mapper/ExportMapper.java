package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topsun.model.OperationModel;

public interface ExportMapper {
	
//	//运维统计
	List<OperationModel> listOperation(Map<String, Object> map);

}
