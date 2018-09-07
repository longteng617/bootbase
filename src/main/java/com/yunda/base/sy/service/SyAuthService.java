package com.yunda.base.sy.service;

import com.yunda.base.sy.domain.SyAuthDO;

import java.util.List;
import java.util.Map;

/**
 * 升阳权限控制表
 * 
 * @author long
 * @email zhanghan813@163.com
 * @date 2018-08-31 11:26:10
 */
public interface SyAuthService {
	
	SyAuthDO get(Long id);

	SyAuthDO queryByAuthCode(String authCode);
	
	List<SyAuthDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SyAuthDO syAuth);
	
	int update(SyAuthDO syAuth);

	int updateMacAddress(String authCode,String macAddress);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
