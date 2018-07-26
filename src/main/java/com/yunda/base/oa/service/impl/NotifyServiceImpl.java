package com.yunda.base.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunda.base.common.service.DictService;
import com.yunda.base.common.utils.DateUtils;
import com.yunda.base.common.utils.PageUtils;
import com.yunda.base.common.utils.Query;
import com.yunda.base.oa.dao.NotifyDao;
import com.yunda.base.oa.dao.NotifyRecordDao;
import com.yunda.base.oa.domain.NotifyDO;
import com.yunda.base.oa.domain.NotifyDTO;
import com.yunda.base.oa.domain.NotifyRecordDO;
import com.yunda.base.oa.service.NotifyService;
import com.yunda.base.system.dao.UserDao;

@Service
public class NotifyServiceImpl implements NotifyService {
	@Autowired
	private NotifyDao notifyDao;

	@Autowired
	private NotifyRecordDao recordDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DictService dictService;

	@Override
	public NotifyDO get(Long id) {
		NotifyDO rDO = notifyDao.get(id);
		rDO.setType(dictService.getName("oa_notify_type", rDO.getType()));
		return rDO;
	}

	@Override
	public List<NotifyDO> list(Map<String, Object> map) {
		List<NotifyDO> notifys = notifyDao.list(map);
		for (NotifyDO notifyDO : notifys) {
			notifyDO.setType(dictService.getName("oa_notify_type", notifyDO.getType()));
		}
		return notifys;
	}

	@Override
	public int count(Map<String, Object> map) {
		return notifyDao.count(map);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int save(NotifyDO notify) {
		notify.setUpdateDate(new Date());
		int r = notifyDao.save(notify);
		// 保存到接受者列表中
		Long[] userIds = notify.getUserIds();
		Long notifyId = notify.getId();
		List<NotifyRecordDO> records = new ArrayList<>();
		for (Long userId : userIds) {
			NotifyRecordDO record = new NotifyRecordDO();
			record.setNotifyId(notifyId);
			record.setUserId(userId);
			record.setIsRead(0);
			records.add(record);
		}
		recordDao.batchSave(records);
		return r;
	}

	@Override
	public int update(NotifyDO notify) {
		return notifyDao.update(notify);
	}

	@Transactional
	@Override
	public int remove(Long id) {
		recordDao.removeByNotifbyId(id);
		return notifyDao.remove(id);
	}

	@Transactional
	@Override
	public int batchRemove(Long[] ids) {
		recordDao.batchRemoveByNotifbyId(ids);
		return notifyDao.batchRemove(ids);
	}

	@Override
	public PageUtils selfList(Map<String, Object> map) {
		List<NotifyDTO> rows = notifyDao.listDTO(map);
		for (NotifyDTO notifyDTO : rows) {
			notifyDTO.setBefore(DateUtils.getTimeBefore(notifyDTO.getUpdateDate()));
			notifyDTO.setSender(userDao.get(notifyDTO.getCreateBy()).getName());
		}
		PageUtils page = new PageUtils(rows, notifyDao.countDTO(map));
		return page;
	}

	@Override
	public List<NotifyDO> listNotify(Query query) {
		return notifyDao.listNotify(query);
	}

}
