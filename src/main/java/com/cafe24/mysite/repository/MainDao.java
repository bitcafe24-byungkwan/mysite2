package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.MainVo;

@Repository
public class MainDao {
	@Autowired
	private SqlSession sqlSession;

	public MainVo get() {
		return sqlSession.selectOne("site.get");
	}
	
}
