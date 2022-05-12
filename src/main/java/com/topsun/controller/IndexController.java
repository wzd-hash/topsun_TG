package com.topsun.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.topsun.entity.*;
import com.topsun.service.BusService;
import com.topsun.service.DataLocationService;
import com.topsun.service.UserService;
import com.topsun.util.DateUtils;
import com.topsun.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class IndexController extends BaseController{
	
	@Autowired
	private LogUtil logUtil;
	
	@Autowired
	private BusService busService;
	
	
	@Autowired
	private DataLocationService dataLocationService;
	
	@Autowired
	private UserService userService;
	
	@Value("${video.inputurl.ip}")
	private String inputIp;
	
	@Value("${video.inputurl.port}")
	private String inputPort;
	
	@Value("${video.outputurl}")
	private String outputUrl;
	
	@RequestMapping("/login")
	public Object login(User user) {
		//创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		//创建主题
		Subject subject = SecurityUtils.getSubject();
		//用于返回信息的map
		Map<String, Object> map = new HashMap<String, Object>();
//		try {
			//使用令牌登陆
			subject.login(token);
			//获取用户
		    User userByUsername = (User)subject.getPrincipal();
		    List<Menu> menus = null;
			
			//将用户加入session
		    subject.getSession().setTimeout(28800000L);
			subject.getSession().setAttribute("user", userByUsername);
			
			Map<String, Integer> upmap = new HashMap<>();
			upmap.put("authLeave", userByUsername.getRoles().getAuthLeave());
			upmap.put("companyID", userByUsername.getRoles().getCompany().getCompanyID());
			//判断是否为江宁的账号
			Map<String, Object> jnMap = new HashMap<String, Object>();
			if (userByUsername.getHeadCompanyID() == 24&&userByUsername.getUserName().equals("jnadmin")) {
				jnMap.put("userName", "adminOne");
				List<User> jnUser = userService.listUsers(jnMap);
				String manRange = jnUser.get(0).getRoles().getManRange();
				//查询菜单
				menus = userService.listMenu(manRange.split(","));
			}
			//江宁普通管理员菜单
			if(userByUsername.getRoles().getAuthLeave()==2&&userByUsername.getHeadCompanyID() == 24){
				String manRange = ("56,57,58,4,52,62,11,12,48,49,50,51,53,54,55,59");
				menus = userService.listMenu(manRange.split(","));
			}
			if(userByUsername.getRoles().getAuthLeave()==3&&userByUsername.getHeadCompanyID() == 24){
				jnMap.put("userName", userByUsername.getUserName());
				List<User> jnUser = userService.listUsers(jnMap);
				String manRange = jnUser.get(0).getRoles().getManRange();
				//查询菜单
				menus = userService.listMenu(manRange.split(","));
			}
			//非江宁用户登录
			if(userByUsername.getHeadCompanyID() != 24){
				String manRange = ("56,57,58,4,52,62,11,12,48,49,50,51,53,54,55,59");
				menus = userService.listMenu(manRange.split(","));
			}


			logUtil.addLog("用户:" + user.getUserName() + "登录成功!", 1, getSessionInfo().get("ip"));


			//返回状态码
			map.put("code", 1);
			map.put("message", "成功");
			map.put("user", userByUsername);
			map.put("menuList", menus);
			return map;
		} /*catch(Exception a) {
			map.put("code", 2);
			map.put("message", "用户名或密码错误");
			return map;
		}
	}*/
	
	@RequestMapping("/logout")
	public Object logout(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Subject subject = SecurityUtils.getSubject();
			
			
			subject.getSession().removeAttribute("user");
			subject.logout();
			map.put("code", 0);
			map.put("msg", "success");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "fail");
		} 
		return map;
	}
	
	@RequestMapping("getCurrentData")
	public Object getCurrentData(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();
		String busID = request.getParameter("busID");
		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");
		String roulesID = request.getParameter("roulesID");
		
		
		if(StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID.split(","));
		}
			
		if (StringUtils.isNotEmpty(roulesID)) {
			map.put("roulesID", roulesID.split(","));
		}
		
		if (StringUtils.isNotEmpty(subID)) {
			map.put("subID", subID.split(","));
		}
		
		if (StringUtils.isNotEmpty(headID)) {
			map.put("headID", headID.split(","));
		}

		List<DataLocation> list = dataLocationService.listBusData(map);
		return list;
		
	}

	//
	@RequestMapping("getBusCurrentData")
	public Object getBusCurrentData(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();
		String busID = request.getParameter("busID");
		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");
		String roulesID = request.getParameter("roulesID");
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		if(StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID.split(","));
		}
		if (StringUtils.isNotEmpty(roulesID)) {
			map.put("roulesID", roulesID.split(","));
		}
		if (StringUtils.isNotEmpty(subID)) {
			map.put("subID", subID.split(","));
		}
		if (StringUtils.isNotEmpty(headID)) {
			map.put("headID", headID.split(","));
		}
		Integer count = dataLocationService.listBusCurrentDataCount(map);
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			map.put("page", (Integer.parseInt(page) - 1) * Integer.parseInt(limit));
			map.put("limit", Integer.parseInt(limit));
		}

		List<BusCurrentData> list = dataLocationService.listBusCurrentData(map);

		map.clear();
		map.put("total", count);
		map.put("list", list);

		return map;
	}

	@RequestMapping("/getBusCurrentData1")
	public Object getBusCurrentData1(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();
		String busID = request.getParameter("busID");
		String headID = request.getParameter("headID");
		String subID = request.getParameter("subID");
		String roulesID = request.getParameter("roulesID");

		if(StringUtils.isNotEmpty(busID)) {
			map.put("busID", busID.split(","));
		}
		if (StringUtils.isNotEmpty(roulesID)) {
			map.put("roulesID", roulesID.split(","));
		}
		if (StringUtils.isNotEmpty(subID)) {
			map.put("subID", subID.split(","));
		}
		if (StringUtils.isNotEmpty(headID)) {
			map.put("headID", headID.split(","));
		}
		List<TrackBackTest> list = dataLocationService.listBusCurrentDataHome(map);

		return list;
	}


	
	/**
	 * 首页信息
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@RequestMapping("/getIndexMessage")
	public Object getIndexMessage() throws NoSuchFieldException, SecurityException {
		
		Map<String, Object> map = getSessionInfo();

		ArrayList<Data> array = new ArrayList<Data>();
		
		Data allBusNum = new Data();
		allBusNum.setType("车辆总数");
		allBusNum.setNum(busService.getAllBusNum(map)+"");
		
		Data workNum = new Data();
		workNum.setType("营运车辆数");
		workNum.setNum(busService.getWorkNum(map)+"");
		
		Data busOnLineNum = new Data();
		busOnLineNum.setType("在线");
		map.put("isNow", 1);
		busOnLineNum.setNum(busService.getOnLineBusNum(map)+"");
		
		Data Online = new Data();
		Online.setType("在线率");
		if(Integer.parseInt(busOnLineNum.getNum()) == 0) {
			Online.setNum(0.00+"");
		}else {
			Online.setNum(String.format("%.2f", Double.valueOf(busOnLineNum.getNum())/Double.valueOf(allBusNum.getNum())*100));
		}
		
		//行驶（ACC）
		map.put("acc", 1);
		Data getRunBusNum = new Data();
		getRunBusNum.setType("行驶");
		getRunBusNum.setNum(busService.getRunBusNum(map)+"");
		array.add(getRunBusNum);
		
		
		//停车
		map.put("acc", 2);
		Data getStopBusNum = new Data();
		getStopBusNum.setType("停车");
		getStopBusNum.setNum(busService.getRunBusNum(map)+"");
		array.add(getStopBusNum);
		
		
//		Data onLineBusAlarmNum = new Data();
//		onLineBusAlarmNum.setType("异常");
//		onLineBusAlarmNum.setNum(busService.getExceptionBus(map)+"");
		
		Data offBusNum = new Data();
		offBusNum.setType("离线");
		offBusNum.setNum((Integer.parseInt(allBusNum.getNum()) -Integer.parseInt( busOnLineNum.getNum()))+"");
		array.add(offBusNum);
		
		
		Data NoGPS = new Data();
		NoGPS.setType("不定位");
		NoGPS.setNum(busService.getNoGpsNum(map)+"");
		
//		array.add(onLineBusAlarmNum);
		array.add(NoGPS);




		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("array", array);
		hashMap.put("allBusNum",allBusNum);
		hashMap.put("busOnLineNum", busOnLineNum);
		hashMap.put("workNum", workNum);
		hashMap.put("Online", Online);
		
		
		return hashMap;
	}
	
	
	@RequestMapping("/getCurrentData1")
	public Object getCurrentData1(HttpServletRequest request) {
		Map<String, Object> map = getSessionInfo();
			
		List<TrackBackTest> list = dataLocationService.listBusDataHome(map);


		
		return list;
		
	}
	
	
	@RequestMapping("/getNewVideo")
	public Object getNewVideo() {
		Map<String, Object> map = getSessionInfo();
			
		map.put("tableName", "bus_alarm_"+DateUtils.getFirstLastDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		map.put("databaseName", "alarm_topsun");
		String url = dataLocationService.getNewVideo(map);

		url = "http://ztc.njtopsun.com" + url.substring(8, url.length());

		map.clear();
		map.put("url", url);
		return map;

	}

	/*
	 * 查询角色信息
	 * 2019-12-09
	 */
	@RequestMapping("/listRoles")
	public Object listRoles(HttpServletRequest request) {

		Map<String, Object> map = getSessionInfo();
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");

		//总公司ID
		String companyID = "24";
		String authLeave = "3";
		@SuppressWarnings("resource")
		Page<Object> pageHelper = new Page<>();
		if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}
		if (StringUtils.isNotEmpty(companyID)) {
			map.put("companyID", companyID);
		}
		if (StringUtils.isNotEmpty(authLeave)) {
			map.put("authority", authLeave);
		}

		List<Roles> list = userService.listRoles(map);
		map.clear();
		map.put("list", list);
		map.put("code", 0);
		map.put("msg", "success");
		map.put("total", pageHelper.getTotal());
		return map;
	}

	/*
	 * 新增角色
	 * 2019-12-09
	 */
	@RequestMapping("/addRoles")
	public Object addRoles(Roles roles, HttpServletRequest request) {

		Map<String, Object> map = getSessionInfo();

		String companyID = "24";

		Company company = new Company();
		company.setCompanyID(Integer.parseInt(companyID));
		roles.setCompany(company);

		map.put("roles", roles);

		try {
			map = userService.addRoles(map);

		} catch (Exception e) {
			map.clear();
			map.put("code", 2);//系统报错
		}

		return map;
	}


	/*
	 * 编辑角色
	 * 2019-12-9
	 */
	@RequestMapping("/editRoles")
	public Object editRoles(Roles roles) {

		String optType = request.getParameter("optType");//操作类型 1-修改 2-删除
		Map<String, Object> map = getSessionInfo();

		String companyID = "24";
		if (StringUtils.isNotEmpty(companyID)) {
			Company company = new Company();
			company.setCompanyID(Integer.parseInt(companyID));
			roles.setCompany(company);
		}

		map.put("roles", roles);
		map.put("optType", optType);
		try {
			map = userService.editRoles(map);
		} catch (Exception e) {
			map.clear();
			map.put("code", 2);//系统报错
		}

		return map;
	}


	/*
	 * 查询用户信息
	 * 2019-12-10
	 */
	@RequestMapping("/listUsers")
	public Object listUsers(HttpServletRequest request) {

		Map<String, Object> map = getSessionInfo();

		String companyID = "24";

		String authLeave = "3";

		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		@SuppressWarnings("resource")
		Page pageHelper = new Page<>();
		if (StringUtils.isEmpty(page) || StringUtils.isEmpty(limit)) {
			map.put("code", 1);
			map.put("msg", "缺少分页参数");
			return map;
		} else {
			pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		}

		if (StringUtils.isNotEmpty(authLeave) && StringUtils.isNotEmpty(companyID)) {
				map.put("authLeave", Integer.parseInt(authLeave));
				map.put("companyID", Integer.parseInt(companyID));
		}


		List<User> list = userService.listUsers(map);

		map.clear();
		map.put("list", list);
		map.put("total", pageHelper.getTotal());
		map.put("code", 0);
		map.put("msg", "success");

		return map;
	}

	/*
	 * 新增用户
	 * 2019-12-10
	 */
	@RequestMapping("/addUsers")
	public Object addUsers(HttpServletRequest request) {

		Map<String, Object> map = getSessionInfo();
		//用户名称
		String userName = request.getParameter("userName");
		//密码
		String password = request.getParameter("password");
		//角色ID
		String roleID = request.getParameter("roleID");
		//所属组织
		String companyID = "24";
		//联系人
		String person = request.getParameter("person");
		//联系电话
		String phoneNum = request.getParameter("phoneNum");

		//判断角色是否是集团账号
		Map<String, Object> rolesMap = new HashMap<>();
		rolesMap.put("roleID", roleID);
		List<Roles> roles = userService.listRoles(rolesMap);
		/*if (roles != null && roles.size() > 0) {
			if (roles.get(0).getAuthLeave() == 1) {
				map.put("headCompanyID", companyID);
			} else {
				map.put("headCompanyID", roles.get(0).getCompany().getCompanyID());
			}
		}*/

		map.put("userName", userName);
		map.put("password", password);
		map.put("roleID", roleID);
		map.put("companyID", companyID);
		map.put("person", person);
		map.put("phoneNum", phoneNum);
		try {
			map = userService.addUsers(map);
			/*if ((int) map.get("code") == 0) {
				Map<String, Integer> upmap = new HashMap<>();
				upmap.put("authLeave", roles.get(0).getAuthLeave());
				upmap.put("companyID", Integer.parseInt(companyID));
				new StatisticsTime(upmap).start();
			}*/
		} catch (Exception e) {
			map.clear();
			map.put("code", 2);//系统报错
		}
		return map;
	}

	/*
	 * 编辑用户信息
	 * 2019-12-10
	 */
	@RequestMapping("/editUsers")
	public Object editUsers(HttpServletRequest request) {

		String optType = request.getParameter("optType");//操作类型 1-修改 2-删除

		Map<String, Object> map = getSessionInfo();
		//用户ID
		String userID = request.getParameter("userID");
		//用户名称
		String userName = request.getParameter("userName");
		//角色ID
		String roleID = request.getParameter("roleID");
		//所属组织
		String companyID = "24";
		//联系人
		String person = request.getParameter("person");
		//联系电话
		String phoneNum = request.getParameter("phoneNum");
		//原始密码
		String oldPassword = request.getParameter("oldPassword");
		//新密码
		String newPassword = request.getParameter("newPassword");

		map.put("userID", Integer.parseInt(userID));
		map.put("userName", userName);
		map.put("roleID", roleID);
		map.put("companyID", companyID);
		map.put("person", person);
		map.put("phoneNum", phoneNum);
		map.put("optType", optType);
		map.put("oldPassword", oldPassword);
		map.put("newPassword", newPassword);
		try {
			map = userService.editUsers(map);
		} catch (Exception e) {
			map.clear();
			map.put("code", 2);//系统报错
		}
		return map;
	}


}
