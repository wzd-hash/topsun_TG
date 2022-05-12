package com.topsun.mapper;

import java.util.List;
import java.util.Map;

import com.topsun.entity.Roles;
import org.apache.ibatis.annotations.Param;

import com.topsun.entity.Menu;
import com.topsun.entity.User;


public interface UserMapper {

	User getUserByUserName(@Param("map")Map<String, Object> map); 
	
	List<User> listUsers(@Param("map")Map<String, Object> map);
	
	List<Menu> listMenu(String[] array);


	List<Roles> listRoles(@Param("map") Map<String, Object> map);

	Integer addRoles(@Param("roles") Roles roles);

	Integer editRoles(Roles roles);

	Integer addUsers(@Param("map") Map<String, Object> map);

	Integer editUsers(@Param("map") Map<String, Object> map);

}
