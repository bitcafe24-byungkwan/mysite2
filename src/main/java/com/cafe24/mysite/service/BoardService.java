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
	
	public Long write(BoardVo vo) {
		if(vo.getNo() == null) //origin
		{			
			boardDao.insert(vo);
			
		}
		else   //rep
		{
			Long id = vo.getNo();
			BoardVo oldVo = getWriting(id);

			vo.setGroupNo(oldVo.getGroupNo());
			vo.setOrderNo(oldVo.getOrderNo()+1);
			vo.setDepth(oldVo.getDepth()+1);
			
			if(!boardDao.pushOrderNum(vo))
				return -1L;	
			boardDao.insertRefly(vo);
		}
		return vo.getNo();
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
	
	public Boolean disableWriting(Long no) {
		return boardDao.disable(no);		
	}
}
