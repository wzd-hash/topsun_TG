package com.topsun.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.alibaba.fastjson.JSONObject;
/**
 * 未登录拦截
 * @author Administrator
 *
 */
public class NotLoginFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		HttpServletResponse res = (HttpServletResponse) response;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 44);
		map.put("message", "请登录");
		String jsonString = JSONObject.toJSONString(map);
		response.setContentType("application/json;charset=utf-8");
		res.getWriter().write(jsonString);
		
		return false;
	}

}
