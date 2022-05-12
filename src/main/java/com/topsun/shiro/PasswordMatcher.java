package com.topsun.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class PasswordMatcher extends SimpleCredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//获取令牌
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		//获取令牌密码
		String pwd = new String(upt.getPassword());
		//获取数据库密码
		String credentials = (String)info.getCredentials();
		//对比
		return equals(pwd,credentials);
	}

	
}
