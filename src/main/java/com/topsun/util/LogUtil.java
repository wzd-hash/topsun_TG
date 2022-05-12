package com.topsun.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topsun.entity.Log;
import com.topsun.entity.User;
import com.topsun.service.LogService;

@Component
public class LogUtil {
	
	
	@Autowired
	private LogService logService;
	
	
	
	/**
	 * 添加日志
	 * @param content 内容
	 * @param type 日志类别 
	 * @return
	 */
	public boolean addLog(String content,Integer type,Object ip) {
		Map<String, Integer> sessionInfo = getSessionInfo();
		Integer userID = (Integer)sessionInfo.get("userID");
		Integer headCompanyID = (Integer)sessionInfo.get("headCompanyID");
		Integer companyID = (Integer)sessionInfo.get("companyID");
		Integer authLeave = (Integer)sessionInfo.get("authLeave");

		
		Log log = new Log();
		log.setUserID(userID);
		
		
		if(authLeave != 2) {
			log.setHeadID(companyID);
		}else {
			log.setSubID(companyID);
			log.setHeadID(headCompanyID);
		}
		
		log.setAuthLeave(authLeave);
		
		log.setContent(content);
		log.setOptType(type);
		
		log.setLoginIP((String)ip);
		
		
		
		return logService.addLog(log);
		
	}
	
	
	
	
	
	
	public Map<String, Integer> getSessionInfo(){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//创建主题
		Subject subject = SecurityUtils.getSubject();
		
		User user = (User) subject.getSession().getAttribute("user");
		if (user!=null) {
			map.put("companyID", user.getRoles().getCompany().getCompanyID());
			map.put("authLeave", user.getRoles().getAuthLeave());
			map.put("userID", user.getUserID());
			map.put("headCompanyID", user.getHeadCompanyID());
		}
		
		return map;
	}
}
