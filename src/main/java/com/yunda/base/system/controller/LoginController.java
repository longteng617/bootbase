package com.yunda.base.system.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunda.base.common.annotation.Log;
import com.yunda.base.common.config.AutoBaseConfig;
import com.yunda.base.common.controller.BaseController;
import com.yunda.base.common.domain.FileDO;
import com.yunda.base.common.domain.Tree;
import com.yunda.base.common.service.FileService;
import com.yunda.base.common.utils.MD5Utils;
import com.yunda.base.common.utils.R;
import com.yunda.base.common.utils.ShiroUtils;
import com.yunda.base.system.domain.MenuDO;
import com.yunda.base.system.service.MenuService;
import com.yunda.base.common.utils.DesEncrypt;
@Controller
public class LoginController extends BaseController {
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;

	@Autowired
	FileService fileService;

	@Autowired
	AutoBaseConfig baseConfig;

	@GetMapping({ "/bootbase", "" })
	String welcome(Model model) {
		return "redirect:/login";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if (fileDO != null && fileDO.getUrl() != null) {
			if (fileService.isExist(fileDO.getUrl())) {
				model.addAttribute("picUrl", baseConfig.getUploadLocal() + fileDO.getUrl());
			} else {
				model.addAttribute("picUrl", "/bootbase/img/photo_s.jpg");
			}
		} else {
			model.addAttribute("picUrl", "/bootbase/img/photo_s.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		try {
			password = DesEncrypt.decryption(password, "12345678");
			username = DesEncrypt.decryption(username, "12345678");
			password = MD5Utils.encrypt(username, password);
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			return R.ok();
		} catch (Exception e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

	@GetMapping("/403")
	String error403() {
		return "403";
	}

}
