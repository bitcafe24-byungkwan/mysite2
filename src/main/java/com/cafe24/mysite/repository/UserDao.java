package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.UserVo;
@Repository
public class UserDao {
	public UserDao() {
		System.out.println("Userdao construc");
	}
	public Boolean update(UserVo vo) {
		Boolean res = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String tempQuery = "";
			if(vo.getName().length()>0)
				tempQuery += String.format(",name = '%s' ", vo.getName());
			if(vo.getPassword().length()>0)
				tempQuery += String.format(",password = '%s' ", vo.getPassword());
			conn = getConnection();

			String sql = "update user \r\n" + 
					" set gender = ? \r\n" +
					 tempQuery +
					" where no= ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getGender());
			pstmt.setLong(2, vo.getNo());
			
			int count = pstmt.executeUpdate();			
			res = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {	
			try {

				if(pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	
	
	
	
	
	
	public UserVo get(Long no) {
		UserVo res = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select name,email,gender from user where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);			
			rs = pstmt.executeQuery();

			if(rs.next() ) {				
				String name = rs.getString(1);
				String email = rs.getString(2);
				String gender = rs.getString(3);
				
				res = new UserVo();
				res.setNo(no);
				res.setGender(gender);
				res.setName(name);	
				res.setEmail(email);
			}
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
	
			try {
				if ( rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public UserVo get(String email, String password) {
		UserVo res = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select no, name from user where email=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if(rs.next() ) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				res = new UserVo();
				res.setNo(no);			
				res.setName(name);				
			}
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
	
			try {
				if ( rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	
	public Boolean insert(UserVo vo) {
		Boolean res = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = getConnection();

			String sql = "insert into user\r\n" + 
					" values(null,?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			int count = pstmt.executeUpdate();			
			res = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {	
			try {

				if(pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
 
				e.printStackTrace();
			}
		}

		return res;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://lx01:3307/webdb";

			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}		
		
		return conn;
	}
}
