package com.yunda.base.sy.controller;

import com.yunda.base.common.controller.BaseController;
import com.yunda.base.common.utils.MacAddressUtils;
import com.yunda.base.common.utils.PageUtils;
import com.yunda.base.common.utils.Query;
import com.yunda.base.common.utils.R;
import com.yunda.base.sy.domain.SyAuthDO;
import com.yunda.base.sy.service.SyAuthService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 升阳权限控制表
 * 
 * @author long
 * @date 2018-08-31 11:26:10
 */
 
@Controller
@RequestMapping("/sy/syAuth")
public class SyAuthController extends BaseController {
	@Autowired
	private SyAuthService syAuthService;
	
	@GetMapping()
	@RequiresPermissions("sy:syAuth:syAuth")
	String SyAuth(){
	    return "sy/syAuth/syAuth";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sy:syAuth:syAuth")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SyAuthDO> syAuthList = syAuthService.list(query);
		int total = syAuthService.count(query);
		PageUtils pageUtils = new PageUtils(syAuthList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sy:syAuth:add")
	String add(){
	    return "sy/syAuth/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sy:syAuth:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SyAuthDO syAuth = syAuthService.get(id);
		model.addAttribute("syAuth", syAuth);
	    return "sy/syAuth/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sy:syAuth:add")
	public R save(SyAuthDO syAuth){
		syAuth.setCreateBy(getUsername());
		syAuth.setCreateTime(new Date());
		syAuth.setDeleteFlag(0);
		if(syAuthService.save(syAuth)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sy:syAuth:edit")
	public R update( SyAuthDO syAuth){
		syAuth.setUpdateBy(getUsername());
		syAuth.setUpdateTime(new Date());
		syAuthService.update(syAuth);
		return R.ok();
	}
    /**
     * 修改Mac地址
     */
    @ResponseBody
    @RequestMapping(value = "/update-mac-address",method = {RequestMethod.GET,RequestMethod.POST})
	public R updateMacAddress(HttpServletRequest request,String authCode){
		String remoteIP = MacAddressUtils.getIpAddr(request);
        String macAddress = MacAddressUtils.getMACAddress(remoteIP);
        syAuthService.updateMacAddress(authCode,macAddress);
        return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sy:syAuth:remove")
	public R remove( Long id){
		if(syAuthService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sy:syAuth:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		syAuthService.batchRemove(ids);
		return R.ok();
	}
	
}
