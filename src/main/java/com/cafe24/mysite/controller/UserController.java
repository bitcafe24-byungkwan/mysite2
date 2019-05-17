package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		//System.out.println(userVo);
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value = "/joinsuccess")
	public String joinSuccess() {
		
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET )
	public String login() {
		return "/user/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST )
	public String login(
			@RequestParam(value="email",required=true,defaultValue = "") String email,
			@RequestParam(value="password",required=true,defaultValue = "") String password,
			HttpSession session,
			Model model) {
		//UserVo authUser =userDao.get();		
		UserVo authUser = userService.getUser(new UserVo(email, password));
		if(authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		
		// session 처리
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET )
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET )
	public String update(HttpSession session, Model model) {
		if(session == null) {			
			return "redirect:/";
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");		
		if(authUser == null) {
			return "redirect:/";
		}
		
		Long userNo = authUser.getNo();
		UserVo userVo = userService.getUser(userNo);
		model.addAttribute("userVo", userVo);

		return "user/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST )
	public String update(@ModelAttribute UserVo userVo, HttpSession session,
			Model model) {

		if(!userService.updateUser(userVo)) {
			
			model.addAttribute("userVo", userVo);
			model.addAttribute("result", "fail");
			//WebUtil.forward(request, response, "WEB-INF/views/user/updateform.jsp");
			return "user/update";
		}
		
		model.addAttribute("result", "success");
		session.setAttribute("authUser", userVo);
		
		return "user/update";
	}

}
