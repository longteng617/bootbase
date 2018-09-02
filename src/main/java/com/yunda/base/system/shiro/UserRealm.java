package com.yunda.base.system.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yunda.base.common.exception.BusinessException;
import com.yunda.base.common.exception.SyAuthException;
import com.yunda.base.common.utils.MacAddressUtils;
import com.yunda.base.common.utils.StringUtil;
import com.yunda.base.common.utils.StringUtils;
import com.yunda.base.sy.dao.SyAuthDao;
import com.yunda.base.sy.domain.SyAuthDO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunda.base.common.config.ApplicationContextRegister;
import com.yunda.base.common.utils.ShiroUtils;
import com.yunda.base.system.dao.UserDao;
import com.yunda.base.system.domain.UserDO;
import com.yunda.base.system.domain.UserToken;
import com.yunda.base.system.service.MenuService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<>(16);
		map.put("username", username);
		String password = new String((char[]) token.getCredentials());

		UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
		// 查询用户信息
		List<UserDO> list = userMapper.list(map);

		// 账号不存在
		if (list.isEmpty()) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(list.get(0).getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (list.get(0).getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}

		//授权码判定
		SyAuthDao syAuthMapper = ApplicationContextRegister.getBean(SyAuthDao.class);
		SyAuthDO syAuthDO = syAuthMapper.queryByUsername(username);
		if(syAuthDO==null){
			throw new SyAuthException("非法用户");
		}
		String macAddressDB = syAuthDO.getMacAddress();
		if(StringUtil.isNullStr(macAddressDB)){
            throw new SyAuthException("用户没有激活，请先激活");
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String remoteIP = MacAddressUtils.getRemoteIP(request);
        String macAddress = MacAddressUtils.getMACAddress(remoteIP);
        if(!macAddressDB.equals(macAddress)){
            throw new SyAuthException("该电脑没有授权");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(list.get(0), password, getName());
		return info;
	}

}
