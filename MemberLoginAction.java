package com.exam.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.MemberDao;

public class MemberLoginAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction");
		
		//파라미터 값 가져오기(id/passwd/rememberMe)
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		//체크박스, 라디오 박스처럼 선택하지 않으면 null을 리턴함
		String rememberMe = request.getParameter("rememberMe");
		
		
		
		//DAO객체 준비
		MemberDao memberDao = MemberDao.getInstance();
		//사용자 확인 메소드 호출
		int check = memberDao.userCheck(id,passwd);
		//chek==1이면 로그인 인증(세션값생성 id). index.jsp로 이동)
		//chek==0이면 패스워드가 틀림뒤로 이동
		//chek==-1이면 아이디없음 뒤로 이동
		
		
		
		if(check == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.print("alert('패스워드가 다릅니다.');");
			out.print("histroy.back();");
			out.println("</script>");
			//out.flush();
			out.close();
			return null;
			
		}else if(check == -1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.print("alert('존재하지않는 아이디 입니다.');");
			out.print("histroy.back();");
			out.println("</script>");
			out.close();
			return null;
			
			
		}
		
		//로그인 성공일때
		// 로그인 인증
		HttpSession session = request.getSession();
		session.setAttribute("id", id);

		// 로그인 상태유지 여부 확인
		if (rememberMe != null && rememberMe.equals("true")) {
			// 쿠키객체 생성해서 응답시 보내기
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60 * 10);// 초단위 10분 = 60*10 = 600초

			cookie.setPath("/"); // 쿠키 최상위 경로 강제 설정

			response.addCookie(cookie); // 응답객체에 추가
		}

		//main.do 로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(true);
		return forward;
	}

}
