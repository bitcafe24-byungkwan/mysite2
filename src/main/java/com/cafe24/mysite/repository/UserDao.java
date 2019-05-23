package com.cafe24.mysite.repository;


import java.util.HashMap;
import java.util.Map;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.UserVo;
@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public UserDao() {
		System.out.println("Userdao construc");
		
	}
	public Boolean update(UserVo vo) {
		int count = sqlSession.update("user.update", vo);
		return 1 == count;
	}	
	
	
	public UserVo get(Long no) {

		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public UserVo get(String email, String password) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo;
	}
	
	
	public Boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);
		return 1 == count;
	}
	
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mariadb://lx01:3307/webdb";
//
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패" + e);
//		}		
//		
//		return conn;
//	}
}
