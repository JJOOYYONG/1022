package com.exam.controller.member;

import java.util.HashMap;
import java.util.Map;

import com.exam.controller.Action;

public class ActionFactory {
	
	private Map<String, Action> map = new HashMap<String, Action>(){
		
	};


	private static ActionFactory instance = new ActionFactory() {
		
	};

	public static ActionFactory getInstance() {
		return instance;
	}
	
	private ActionFactory() {
		map.put("/memberJoinForm.do", new MemberJoinFormAction());
		map.put("/memberJoin.do", new MemberJoinAction());
		map.put("/memberLoginForm.do", new MemberLoginFormAction());
		map.put("/memberLogin.do", new MemberLoginAction());
		map.put("/main.do", new MainAction());
		map.put("/memberLogout.do", new MemberLogoutAction());
		
		
		
	}//생성자
	
	
	public Action getAction(String command) {
		
		
		Action action = null;
		
		action = map.get(command);
		return action;
		
		
//		if(command.equals("/memberJoinForm.do")){
//			//회원가입폼 제공
//			
//			
//			action =new MemberJoinFormAction();
//			
//			
//		}else if(command.equals("/memberJoin.do")) {
//			//회원 가입 처리
//			action = new MemberJoinAction();
//			
//		}else if(command.equals("/memberLogin.do")) {
//			action = new MemberLoginFormAction();
//			}
	}//getAction method
}
