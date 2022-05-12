package com.topsun.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.topsun.entity.User;
import com.topsun.service.UserService;



public class Realm extends AuthorizingRealm{
	
	
	@Autowired
	private UserService userService;
	
	//角色权限和对应权限添加
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取配置中的权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User)principals.getPrimaryPrincipal();
		
		String[] split = user.getRoles().getManRange().split(",");
		
		
		switch(user.getRoles().getAuthLeave()) {
		case 0 : info.addStringPermission("集团");break;
		case 1 : info.addStringPermission("公司");break;
		case 2 : info.addStringPermission("线路");break;
		
		}
		
		for (int i = 0; i < split.length; i++) {
			info.addStringPermission(split[i]);
		}
		
		
		return info;
	}
	//用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 通过令牌得到用户名和密码
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		// 调用登录查询
		User user = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", upt.getUsername());
		map.put("password", String.valueOf(upt.getPassword()));
		user = userService.listUsers(map).get(0);
		if (null != user) {
			// 构造参数1： 主角=登陆用户
			// 参数2：授权码：数据库密码
			// 参数3：realm的名称
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
			
			return info;
		}
		return null;
	}
}
