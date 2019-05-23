package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;
import com.cafe24.mysite.vo.UserVo.UserRole;
import com.cafe24.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. Handler 종류 확인
		if( handler instanceof HandlerMethod == false)
			return true;
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. HandlerMethod에 @Auth 없으면 Class(Type)에 @Auth를 받아오기
		if(auth == null) {
			//auth = handlerMethod.get(Auth.class);		
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}		
		
		//5. @Auth가 안 붙어있는 경우
		if(auth == null) return true;			

		//6. @Auth(in class or method)가 붙어 있기 때문에
		// 		인증 여부 Check
		HttpSession session = request.getSession();
		if(session == null ) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null ) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		//7. Role 가져오기
		Auth.Role role = auth.role();
		
		//8. role이 USER 라면
		//   인증된 모든 사용자는 접근 가능

		if(role == Role.USER) {
			response.sendRedirect(request.getContextPath());
			return true;
		}

		if(role == Role.ADMIN) {
			
			if (authUser.getRole() != UserRole.ADMIN) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
			
		}
		
		//9. Admin Role 접근 check
		//TODO
		//authuser.getrole().equals(admin);
		
		return true;
	}

}
