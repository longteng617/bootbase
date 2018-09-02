package com.yunda.base.sy.service.impl;

import com.yunda.base.common.utils.MajorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunda.base.sy.dao.SyPersonInfoDao;
import com.yunda.base.sy.domain.SyPersonInfoDO;
import com.yunda.base.sy.service.SyPersonInfoService;



@Service
public class SyPersonInfoServiceImpl implements SyPersonInfoService {
	@Autowired
	private SyPersonInfoDao syPersonInfoDao;
	
	@Override
	public SyPersonInfoDO get(Long id){
		return syPersonInfoDao.get(id);
	}
	
	@Override
	public List<SyPersonInfoDO> list(Map<String, Object> map){
		return syPersonInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return syPersonInfoDao.count(map);
	}
	
	@Override
	public int save(SyPersonInfoDO syPersonInfo){
		return syPersonInfoDao.save(syPersonInfo);
	}
	
	@Override
	public int update(SyPersonInfoDO syPersonInfo){
		return syPersonInfoDao.update(syPersonInfo);
	}
	
	@Override
	public int remove(Long id){
		return syPersonInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return syPersonInfoDao.batchRemove(ids);
	}
	
}
