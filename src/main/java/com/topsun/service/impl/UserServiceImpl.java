package com.topsun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topsun.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsun.entity.Menu;
import com.topsun.entity.User;
import com.topsun.mapper.UserMapper;
import com.topsun.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	public User login(Map<String, Object> map) {
		
		return userMapper.getUserByUserName(map);
	}


	public List<User> listUsers(Map<String, Object> map) {
		
		return userMapper.listUsers(map);
	}
	


	@Override
	public List<Menu> listMenu(String[] list) {
		
		return userMapper.listMenu(list);
	}

	public List<Roles> listRoles(Map<String, Object> map) {

		return userMapper.listRoles(map);
	}

	@Transactional(rollbackFor= Exception.class)
	public Map<String, Object> addRoles(Map<String, Object> map) {

		Roles roles = (Roles) map.get("roles");
		String ip = (String) map.get("ip");
		map.put("companyID", roles.getCompany().getCompanyID());
		map.put("authLeave", 1);
		map.put("roleName", roles.getRoleName());
		List<Roles> list = userMapper.listRoles(map);
		map = new HashMap<String, Object>();
		if (list!= null &&list.size()>0) {
			map.put("code", 3);//角色已经存在
			return map;
		}
		Integer result =  userMapper.addRoles(roles);
		if (result>0) {

			//logUtil.addLog("新增角色"+roles.getRoleName(), 2,ip);

			map.put("code", 0);
			map.put("msg", "success");
		}else{
			map.put("code", 1);
			map.put("msg", "fail");
		}
		return map;
	}

	@Transactional(rollbackFor= Exception.class)
	public Map<String, Object> editRoles(Map<String, Object> map) {

		Roles roles = (Roles) map.get("roles");
		String optType = (String) map.get("optType");
		String ip = (String) map.get("ip");
		if ("1".equals(optType)) {
			map.put("companyID", roles.getCompany().getCompanyID());
			map.put("authLeave", 1);
			map.put("roleName", roles.getRoleName());
			List<Roles> list = userMapper.listRoles(map);

			if (list!= null && list.size()>0 && (int)list.get(0).getRoleID()!= (int)roles.getRoleID()) {
				map.clear();
				map.put("code", 3);//角色已经存在
				return map;
			}
			if(list.get(0).getDelStatus()==1){
				map.clear();
				map.put("code", 5);
				return map;
			}
			roles.setDelStatus(0);
		}
		if ("2".equals(optType)) {
			//已经使用的角色不能修改
			Map<String, Object> roleMap = new HashMap<>();
			roleMap.put("roleID", roles.getRoleID());
			List<User> users = userMapper.listUsers(roleMap);
			if (users!=null&&users.size()>0) {
				map.clear();
				map.put("code", 4);//角色已经被绑定不能删除
				return map;
			}
			roles.setDelStatus(1);
		}

		Integer result =  userMapper.editRoles(roles);

		if (result>0) {
			if ("1".equals(optType)) {
				//logUtil.addLog("修改角色:"+roles.getRoleName(), 3,ip);
			}
			if ("2".equals(optType)) {
				//logUtil.addLog("删除角色:"+roles.getRoleName(), 4,ip);
			}
			map.clear();
			map.put("code", 0);
			map.put("msg", "success");
		}else{
			map.clear();
			map.put("code", 1);
			map.put("msg", "fail");
		}
		return map;
	}


	@Transactional(rollbackFor= Exception.class)
	public Map<String, Object> addUsers(Map<String, Object> map) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		//判断用户是否已经存在
		resultMap.put("userName", map.get("userName"));
		List<User> list = userMapper.listUsers(resultMap);
		resultMap.clear();
		if (list!= null &&list.size()>0) {
			resultMap.put("code", 3);//用户已经存在
			return resultMap;
		}

		Integer result =  userMapper.addUsers(map);
		if (result>0) {

			//logUtil.addLog("新增用户:"+map.get("userName"), 2,map.get("ip"));

			resultMap.put("code", 0);
			resultMap.put("msg", "success");
		}else{
			resultMap.put("code", 1);
			resultMap.put("msg", "fail");
		}

		return resultMap;
	}

	public Map<String, Object> editUsers(Map<String, Object> map) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		if ("1".equals(map.get("optType"))) {
			//判断用户是否已经存在
			resultMap.put("userName", map.get("userName"));
			List<User> list = userMapper.listUsers(resultMap);
			resultMap.clear();
			if (list!= null && list.size()>0 &&  (int)list.get(0).getUserID()!= (int)map.get("userID")) {
				resultMap.put("code", 3);//用户已经存在
				return resultMap;
			}

			if (map.get("oldPassword")!=null&&!map.get("oldPassword").equals("")
					&&map.get("newPassword")!=null&&!map.get("newPassword").equals("")) {
				resultMap.put("userId", map.get("userID"));
				resultMap.put("password", map.get("oldPassword"));
				List<User> passwordlist = userMapper.listUsers(resultMap);
				resultMap.clear();
				if (passwordlist==null||passwordlist.size()==0) {
					resultMap.put("code", 4);//密码错误
					return resultMap;
				}
			}


			map.put("delStatus", 0);
		}
		if ("2".equals(map.get("optType"))) {
			map.put("delStatus", 1);
		}
		Integer result =  userMapper.editUsers(map);
		if (result>0) {
			if ("1".equals(map.get("optType"))) {
				//logUtil.addLog("修改用户:"+map.get("userName"), 3,map.get("ip"));
			}
			if ("2".equals(map.get("optType"))) {
				//logUtil.addLog("删除用户:"+map.get("userName"), 4,map.get("ip"));
			}
			resultMap.put("code", 0);
			resultMap.put("msg", "success");
		}else{
			resultMap.put("code", 1);
			resultMap.put("msg", "fail");
		}

		return resultMap;
	}
}
