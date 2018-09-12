package com.yunda.base.system.controller;

import com.yunda.base.common.controller.BaseController;
import com.yunda.base.common.utils.MacAddressUtils;
import com.yunda.base.common.utils.R;
import com.yunda.base.common.utils.StringUtil;
import com.yunda.base.sy.domain.SyAuthDO;
import com.yunda.base.sy.service.SyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ActiveController extends BaseController {

    @Autowired
    private SyAuthService syAuthService;

    @GetMapping({ "/active" })
    public String active() {
        return "/nologin/active";
    }

    @PostMapping("/active")
    @ResponseBody
    public R insertMacAddress(@RequestParam("authCode") String authCode, HttpServletRequest request){
        if(StringUtil.isNullStr(authCode)){
            return R.error("参数不能为空");
        }
        authCode = authCode.trim();
        SyAuthDO syAuthDO = syAuthService.queryByAuthCode(authCode);
        if(syAuthDO ==null){
            return R.error("不存在该授权码");
        }
        if(StringUtil.isNotNullStr(syAuthDO.getMacAddress())){
            return R.error("该授权码已经失效");
        }
        String remoteIP = MacAddressUtils.getIpAddr(request);
        String macAddress = MacAddressUtils.getMACAddress(remoteIP);
//        String macAddress =  MacAddressUtils.getMacAddress(remoteIP);
        if(StringUtil.isNullStr(macAddress)){
            return R.error("调用出现异常");
        }
        syAuthService.updateMacAddress(authCode, macAddress);
        return R.ok();
    }
}
