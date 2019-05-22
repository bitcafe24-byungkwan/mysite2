package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	public Boolean write(BoardVo vo) {
		boardDao.insert(vo);
		return true;
	}
	
	public BoardVo getWriting(Long no) {
		return boardDao.get(no);
	}
	
	public Boolean updateWriting(BoardVo vo) {
		return boardDao.update(vo);
	}

	public List<BoardVo> getList() {		
		return boardDao.getList();
	}
}
