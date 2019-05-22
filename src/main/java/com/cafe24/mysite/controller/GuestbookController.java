package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;





@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
				
		List<GuestbookVo> list = guestbookService.getList();
		
		model.addAttribute("list", list);
		
		return "guestbook/list";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String add(GuestbookVo vo) {
				
		guestbookService.add(vo);		
		return "redirect:/guestbook";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="no",required=true,defaultValue = "") String no
			,Model model) {
		model.addAttribute("no", no);
		
		return "guestbook/delete";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo) {
	
		
		guestbookService.delete(vo);
		return "redirect:/guestbook";
	}
	
	
}
