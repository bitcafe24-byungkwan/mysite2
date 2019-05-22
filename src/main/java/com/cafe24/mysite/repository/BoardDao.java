package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);		
		return 1 == count;		
	}
	
	public BoardVo get(Long no) {
		return sqlSession.selectOne("board.getByNo", no);		
	}
	
	public Boolean update(BoardVo vo) {
		return sqlSession.selectOne("board.update", vo);		
	}

	public List<BoardVo> getList() {
		List<BoardVo> result=sqlSession.selectList("board.getList");
		return result;
	}
}
