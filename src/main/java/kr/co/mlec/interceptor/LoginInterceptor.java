package kr.co.mlec.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import kr.co.mlec.member.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)throws Exception {
		HttpSession session = request.getSession();
		MemberVO userVO = (MemberVO) session.getAttribute("loginInfo");
		if(userVO == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		} 		
		return true;
	}
}
