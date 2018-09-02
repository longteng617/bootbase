package com.yunda.base.sy.dao;

import com.yunda.base.sy.domain.SyAuthDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

/**
 * 升阳权限控制表
 * @author long
 * @date 2018-08-31 11:26:10
 */
@Mapper
public interface SyAuthDao {

	SyAuthDO get(Long id);

	SyAuthDO queryByUsername(String username);
	
	List<SyAuthDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SyAuthDO syAuth);
	
	int update(SyAuthDO syAuth);

	int updateMacAddress(@Param("authCode") String authCode,@Param("macAddress") String macAddress);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
