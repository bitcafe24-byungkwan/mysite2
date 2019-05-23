package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.vo.MainVo;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;


@Auth(role = Role.ADMIN)
@Controller
public class AdminController {	
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin")
	public String site(Model model) {
		MainVo vo = adminService.getMainSettings();
		
		model.addAttribute("mainvalues",vo);
	
		
		return "admin/main";
	}
}
