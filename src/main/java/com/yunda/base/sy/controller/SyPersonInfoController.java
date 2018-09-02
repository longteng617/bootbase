package com.yunda.base.sy.controller;

import com.yunda.base.common.controller.BaseController;
import com.yunda.base.common.utils.MajorKey;
import com.yunda.base.common.utils.PageUtils;
import com.yunda.base.common.utils.Query;
import com.yunda.base.common.utils.R;
import com.yunda.base.sy.domain.SyPersonInfoDO;
import com.yunda.base.sy.service.SyPersonInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 升阳个人信息查询库
 * @author long
 * @date 2018-08-30 14:43:43
 */
 
@Controller
@RequestMapping("/sy/syPersonInfo")
public class SyPersonInfoController extends BaseController {
	@Autowired
	private SyPersonInfoService syPersonInfoService;
	
	@GetMapping()
	@RequiresPermissions("sy:syPersonInfo:syPersonInfo")
	String SyPersonInfo(){
	    return "sy/syPersonInfo/syPersonInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sy:syPersonInfo:syPersonInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SyPersonInfoDO> syPersonInfoList = syPersonInfoService.list(query);
		int total = syPersonInfoService.count(query);
		PageUtils pageUtils = new PageUtils(syPersonInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sy:syPersonInfo:add")
	String add(){
	    return "sy/syPersonInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sy:syPersonInfo:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SyPersonInfoDO syPersonInfo = syPersonInfoService.get(id);
		model.addAttribute("syPersonInfo", syPersonInfo);
	    return "sy/syPersonInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sy:syPersonInfo:add")
	public R save( SyPersonInfoDO syPersonInfo){
		syPersonInfo.setPersonId(String.valueOf(MajorKey.getKeyID()));
		syPersonInfo.setCreateBy(String.valueOf(this.getUserId()));
		syPersonInfo.setCreateTime(new Date());
		syPersonInfo.setDeleteFlag(0);
		if(syPersonInfoService.save(syPersonInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sy:syPersonInfo:edit")
	public R update( SyPersonInfoDO syPersonInfo){
		syPersonInfo.setUpdateBy(String.valueOf(this.getUserId()));
		syPersonInfo.setUpdateTime(new Date());
		syPersonInfoService.update(syPersonInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sy:syPersonInfo:remove")
	public R remove( Long id){
		if(syPersonInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sy:syPersonInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		syPersonInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
