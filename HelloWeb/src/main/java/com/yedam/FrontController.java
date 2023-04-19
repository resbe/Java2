package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	
	//key & value 저장할 수 있는 컬렉션. Map
	Map<String, Control> map;
	
	public FrontController() {
		System.out.println("FrontController() call.");
		map = new HashMap<>();
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() call.");
		map.put("/main.do", new MainControl());
		map.put("/first.do", new FirstControl());
		map.put("/second.do", new SecondControl());
		 //사원정보 상세페이지(getMember.jsp)
		map.put("/getMember.do", new GetMemberControl());
		 //사원정보 수정페이지(modifyMember.jsp)
		map.put("/modifyMember.do", new ModifyMemberControl());
		 //사원정보 추가페이지 jsp를 가기위한 콘트롤러
		 // 주소를 바꾸는 부분ㄴ
		map.put("/addFormControl.do", new AddMemberFormControl());
		 // jsp에서 만들어진 데이터를 보내서 DB에 작용하는 컨트롤러 부분.
		map.put("/addMember.do", new admemberControl());
		 // 사원정보 삭제
		map.put("/deleteMember.do", new DeleteMemberControl());
		 // 로그인 화면 (아이디/이메일 입력화면)
		map.put("/loginForm.do", new LoginFormControl());
		 // 로그인 처리.
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
	
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		System.out.println("service() call.");
		//http://http://localhost:8081/HelloWeb/first.do
		String uri = req.getRequestURI(); //HelloWeb/first.do
		String context = req.getContextPath(); //context: /HelloWeb;
		String page = uri.substring(context.length());
		
		System.out.println(page);
		System.out.println(map.get(page));
		
		Control control = map.get(page);
		control.exec(req, resp);
		
//		Object control = map.get(page);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() call.");
		
	}
	
	
	
}

