package com.yunda.base.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunda.base.sy.dao.SyAuthDao;
import com.yunda.base.sy.domain.SyAuthDO;
import com.yunda.base.sy.service.SyAuthService;



@Service
public class SyAuthServiceImpl implements SyAuthService {
	@Autowired
	private SyAuthDao syAuthDao;
	
	@Override
	public SyAuthDO get(Long id){
		return syAuthDao.get(id);
	}
	
	@Override
	public List<SyAuthDO> list(Map<String, Object> map){
		return syAuthDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return syAuthDao.count(map);
	}
	
	@Override
	public int save(SyAuthDO syAuth){
		return syAuthDao.save(syAuth);
	}
	
	@Override
	public int update(SyAuthDO syAuth){
		return syAuthDao.update(syAuth);
	}

	@Override
	public int updateMacAddress(String authCode, String macAddress) {
		return syAuthDao.updateMacAddress(authCode,macAddress);
	}

	@Override
	public int remove(Long id){
		return syAuthDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return syAuthDao.batchRemove(ids);
	}
	
}
