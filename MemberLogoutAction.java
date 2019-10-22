package com.exam.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;

public class MemberLogoutAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//로그인 상태유지용 쿠키 삭제하기
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					//유효기간 0으로 설정 ->브라우저 해당쿠키 삭제
					cookie.setMaxAge(0);
					//삭제할 쿠키경로도 동일해야함
					cookie.setPath("/");
					response.addCookie(cookie);
				} //if
			} //for
		} //if
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.');");
		out.println("location.href='main.do';");
		out.println("</script>");
		out.close();
		
		
		
		// 세션값 초기화
				HttpSession session=request.getSession();
				session.invalidate();
		
		
		return null;
	}

}
