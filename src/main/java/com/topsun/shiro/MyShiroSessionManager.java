package com.topsun.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

public class MyShiroSessionManager extends DefaultWebSessionManager {

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		 
		 HttpServletRequest req = WebUtils.toHttp(request);
		 String sessionID = req.getParameter("sessionID");
		 if (StringUtils.isEmpty(sessionID)) {
			 return super.getSessionId(request, response);
		 }
		 
		 return sessionID;
	}

	
}
