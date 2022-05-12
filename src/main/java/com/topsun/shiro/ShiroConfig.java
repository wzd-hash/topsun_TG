package com.topsun.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



@Configuration
public class ShiroConfig {
	//Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	 public ShiroFilterFactoryBean webFilter(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>(16);

        //自定义拦截器
        Map<String, Filter> customisedFilter = new HashMap<String, Filter>();
        customisedFilter.put("authc", new NotLoginFilter());
        
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,先配置anon再配置authc。
       filterChainMap.put("/**","anon");
//        filterChainMap.put("/login","anon");
//        filterChainMap.put("/logout","anon");
//        filterChainMap.put("/websocket", "anon");
//        filterChainMap.put("/videoWebSocket", "anon");
//        filterChainMap.put("/upload/downPopup", "anon");
//        filterChainMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilters(customisedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        
        return shiroFilterFactoryBean;
	}
	//权限管理，配置主要是Realm的管理认证
	@Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(realm());
        
      //设置自定义的session管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
	//将自己的验证方式加入容器
	@Bean
	public Realm realm() {
		Realm realm = new Realm();
		realm.setCredentialsMatcher(passwordMatcher());
		return realm;
	}
	
	@Bean
	public PasswordMatcher passwordMatcher() {
		return new PasswordMatcher();
	}
	
	@Bean
	public SessionManager sessionManager() {
		return new MyShiroSessionManager();
		
	}
	
}
