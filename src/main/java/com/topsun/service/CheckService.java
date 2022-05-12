package com.topsun.service;

import java.util.List;
import java.util.Map;

import com.topsun.entity.Check;

public interface CheckService {

//	void addCheck(Map<String, Object> map);

	List<Check> getChecks(Map<String, Object> map);

//	void updateChecks(Map<String, Object> map);

}
