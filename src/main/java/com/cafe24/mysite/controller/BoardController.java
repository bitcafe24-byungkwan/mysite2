package com.cafe24.mysite.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.BoardVo.StatusType;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;
import com.cafe24.security.Auth.Role;
import com.cafe24.mysite.vo.UserVo;



@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = {"/{idx:[\\d]+}"}, method = RequestMethod.GET)
	public String list(@PathVariable Long idx,Model model) {
		List<BoardVo> list = boardService.getList();
		
		model.addAttribute("list", list);
//		for(BoardVo vo : list)
//			System.out.println(vo);
		return "board/list";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list() {
		return "redirect:/board/1";
	}
	
	@Auth(role=Role.USER )
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String addView() {		
		return "board/write";
	}
	
	@Auth(role=Role.USER )
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String add(@ModelAttribute BoardVo boardVo, @AuthUser UserVo authUser,
			Model model) {
//		if(session == null) {			
//			return "redirect:/";
//		}
//		
//		UserVo authUser = (UserVo)session.getAttribute("authUser");	
//		
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		
		boardVo.setUserNo(authUser.getNo());
	
			
		return "redirect:/board/view/" + boardService.write(boardVo);
		
	}
	
	@RequestMapping(value = "/view/{id:[\\d]+}", method = RequestMethod.GET)
	public String view(@PathVariable Long id,Model model) {
		BoardVo vo = boardService.getWriting(id);
		if(vo==null || vo.getStatus()==StatusType.DELETED)
		{
			return "redirect:/board";
		}
		model.addAttribute("vo", vo);
		
		return "board/view";
	}
	
	@Auth(role=Role.USER )
	@RequestMapping(value = "/modify/{id:[\\d]+}", method = RequestMethod.GET)
	public String modifyView(@PathVariable Long id,Model model, @AuthUser UserVo authUser) {
		

		BoardVo vo = boardService.getWriting(id);
		if(authUser == null || 
				vo==null ||
				vo.getStatus()==StatusType.DELETED ||
				authUser.getNo() != vo.getUserNo()) {
			return "redirect:/board";
		}
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	@Auth(role = Role.USER)
	@RequestMapping(value = "/modify/{id:[\\d]+}", method = RequestMethod.POST)
	public String modify(@PathVariable Long id,
			@ModelAttribute BoardVo updateVo,
			Model model) {
		
		BoardVo vo = boardService.getWriting(id);

		if(	vo==null ||
				vo.getStatus()==StatusType.DELETED ||
				updateVo.getUserNo() != vo.getUserNo()) {
			return "redirect:/board";
		}

		System.out.println(updateVo);
		boardService.updateWriting(updateVo);		
		
		return "redirect:/board/view/"+id;
	}
	
	@Auth(role = Role.USER)
	@RequestMapping(value = "/delete/{id:[\\d]+}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id,
			Model model, @AuthUser UserVo authUser) {

		BoardVo vo = boardService.getWriting(id);

		if(authUser == null || 
				vo==null ||
				vo.getStatus()==StatusType.DELETED ||
				authUser.getNo() != vo.getUserNo()) {
			return "redirect:/board";
		}

		boardService.disableWriting(id);		
		return "redirect:/board";
	}
	
	@Auth(role = Role.USER)
	@RequestMapping(value = "/write/{id:[\\d]+}", method = RequestMethod.GET)
	public String addReply(@PathVariable Long id,Model model) {

		BoardVo vo = boardService.getWriting(id);
		
		//System.out.println(vo.toString());
		model.addAttribute("originVo",vo);
		return "board/write";
	}
}
