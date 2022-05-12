package com.topsun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topsun.entity.User;
import com.topsun.util.*;
import org.apache.http.client.utils.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseController {

	@Autowired
	public  HttpServletRequest request;
	
	public static ConcurrentHashMap<String,Object> busMap = new ConcurrentHashMap<String, Object>();
	
	public Map<String, Object> getSessionInfo(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//创建主题
		Subject subject = SecurityUtils.getSubject();
		
		User user = (User) subject.getSession().getAttribute("user");
		if (user!=null) {
			map.put("companyID", user.getRoles().getCompany().getCompanyID());
			map.put("companyName", user.getRoles().getCompany().getCompanyName());
			map.put("authLeave", user.getRoles().getAuthLeave());
			map.put("userID", user.getUserID());
			map.put("headCompanyID", user.getHeadCompanyID());
			map.put("ip", getRealIp());
		}else {
			map.put("companyID", 1);
			map.put("authLeave", 0);
			map.put("userID", 1);
			map.put("headCompanyID", 1);
			map.put("ip", getRealIp());
			
		}
		return map;
		
	}

	public HashMap<String, Object> getHashSessionInfo(){

		HashMap<String, Object> map = new HashMap<String, Object>();

		//创建主题
		Subject subject = SecurityUtils.getSubject();

		User user = (User) subject.getSession().getAttribute("user");
		if (user!=null) {
			map.put("companyID", user.getRoles().getCompany().getCompanyID());
			map.put("companyName", user.getRoles().getCompany().getCompanyName());
			map.put("authLeave", user.getRoles().getAuthLeave());
			map.put("userID", user.getUserID());
			map.put("headCompanyID", user.getHeadCompanyID());
			map.put("ip", getRealIp());
		}else {
			map.put("companyID", 1);
			map.put("authLeave", 0);
			map.put("userID", 1);
			map.put("headCompanyID", 1);
			map.put("ip", getRealIp());

		}
		return map;

	}
	
	
	/**
	 * 获取客户端IP
	 */
	public String  getRealIp() {
		// 这个一般是Nginx反向代理设置的参数
	    String ip = request.getHeader("X-Real-IP");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("X-Forwarded-For");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    // 处理多IP的情况（只取第一个IP）
	    if (ip != null && ip.contains(",")) {
	        String[] ipArray = ip.split(",");
	        ip = ipArray[0];
	    }
	    return ip;
	}

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
		{
			@Override
			public void setAsText(String text)
			{
				setValue(DateUtils.parseDate(text));
			}
		});
	}

	/**
	 * 设置请求分页数据
	 */
	protected void startPage()
	{
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
		{
			String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
			Boolean reasonable = pageDomain.getReasonable();
			PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
		}
	}

	/**
	 * 设置请求排序数据
	 */
	protected void startOrderBy()
	{
		PageDomain pageDomain = TableSupport.buildPageRequest();
		if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
		{
			String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
			PageHelper.orderBy(orderBy);
		}
	}

	/**
	 * 响应请求分页数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected TableDataInfo getDataTable(List<?> list)
	{
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(HttpStatus.SUCCESS);
		rspData.setMsg("查询成功");
		rspData.setRows(list);
		rspData.setTotal(new PageInfo(list).getTotal());
		return rspData;
	}

	/**
	 * 返回成功
	 */
	public AjaxResult success()
	{
		return AjaxResult.success();
	}

	/**
	 * 返回失败消息
	 */
	public AjaxResult error()
	{
		return AjaxResult.error();
	}

	/**
	 * 返回成功消息
	 */
	public AjaxResult success(String message)
	{
		return AjaxResult.success(message);
	}

	/**
	 * 返回失败消息
	 */
	public AjaxResult error(String message)
	{
		return AjaxResult.error(message);
	}

	/**
	 * 响应返回结果
	 *
	 * @param rows 影响行数
	 * @return 操作结果
	 */
	protected AjaxResult toAjax(int rows)
	{
		return rows > 0 ? AjaxResult.success() : AjaxResult.error();
	}

	/**
	 * 响应返回结果
	 *
	 * @param result 结果
	 * @return 操作结果
	 */
	protected AjaxResult toAjax(boolean result)
	{
		return result ? success() : error();
	}

	/**
	 * 页面跳转
	 */
	public String redirect(String url)
	{
		return StringUtils.format("redirect:{}", url);
	}
	
}
