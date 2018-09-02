package com.yunda.base.sy.service;

import com.yunda.base.sy.domain.SyPersonInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 升阳个人信息查询库
 * 
 * @author long
 * @date 2018-08-30 14:43:43
 */
public interface SyPersonInfoService {
	
	SyPersonInfoDO get(Long id);
	
	List<SyPersonInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SyPersonInfoDO syPersonInfo);
	
	int update(SyPersonInfoDO syPersonInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
