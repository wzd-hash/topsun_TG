package com.topsun.service;

import java.util.List;
import java.util.Map;

import com.topsun.entity.Menu;
import com.topsun.entity.Roles;
import com.topsun.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

	User login(Map<String, Object> map);
	List<User> listUsers(Map<String, Object> map);

	List<Menu> listMenu(String[] list);

	List<Roles> listRoles(Map<String, Object> map);

	Map<String, Object> addRoles(Map<String, Object> map);

	Map<String, Object> editRoles(Map<String, Object> map);


	Map<String, Object> addUsers(Map<String, Object> map);

	Map<String, Object> editUsers(Map<String, Object> map);

}
