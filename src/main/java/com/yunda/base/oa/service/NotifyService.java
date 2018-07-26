package com.yunda.base.oa.service;

import java.util.List;
import java.util.Map;

import com.yunda.base.common.utils.PageUtils;
import com.yunda.base.common.utils.Query;
import com.yunda.base.oa.domain.NotifyDO;

/**
 * 通知通告
 * 
 */
public interface NotifyService {

	NotifyDO get(Long id);

	List<NotifyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(NotifyDO notify);

	int update(NotifyDO notify);

	int remove(Long id);

	int batchRemove(Long[] ids);

	PageUtils selfList(Map<String, Object> map);

	List<NotifyDO> listNotify(Query query);
}
