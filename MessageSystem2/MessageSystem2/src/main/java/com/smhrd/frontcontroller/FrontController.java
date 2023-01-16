package com.smhrd.frontcontroller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.controller.Command;
import com.smhrd.controller.JoinProgram;
import com.smhrd.controller.LoginProgram;
import com.smhrd.controller.Logout;
import com.smhrd.controller.SelectAllProgram;
import com.smhrd.controller.UpdateProgram;

// ---> 확장자 패턴 .do로 끝나는 모든 요청을 처리하겠다!
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 초기화 메소드
	@Override
	public void init() throws ServletException {
		// 우리만의 새로운 자료형을 하나 만들자!
		// Collection 중 하나 HashMap - 순서없이 저장되는 가변적인 자료구조(Collection)
		HashMap<String, Command> map = new HashMap<>();
		
		
	}
	
	
	
	
	
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 모든 요청을 한군데에서 처리하게끔 만들기
		// ---> url에 따라서 서로 다른 기능을 수행
		// 		ex) Login.do -> select 쿼리문 실행
		// 			Join.do --> insert 쿼리문 실행
		
		// url구분할 수 있어야 한다. 
		// 1) uri 가져오기
		String uri = request.getRequestURI();
//		System.out.println("요청 uri : " + uri);
		// 2) 프로젝트 경로 가져오기
		String path = request.getContextPath();
//		System.out.println("요청 path : " + path);
		// 3) uri에서 경로만 잘라내기
		String finalurl = uri.substring(path.length() + 1);
//		System.out.println("잘라낸 url : " + finalurl);
		
		// FrontController + Command Pattern + 확장url Pattern
		
		// 1. 모든 일반클래스를 표현할 수 있는 자료형 선언
		Command com = null;
		// 2. 한글 인코딩 잡아주기
		request.setCharacterEncoding("UTF-8");
		
		if(finalurl.equals("Login.do")) 
			com = new LoginProgram();
		else if(finalurl.equals("Join.do")) 
			com = new JoinProgram();
		else if(finalurl.equals("Select.do")) 
			com = new SelectAllProgram();
		else if(finalurl.equals("Logout.do")) 
			com = new Logout();
		else if(finalurl.equals("Update.do"))
			com = new UpdateProgram();
		else 
			System.out.println("요청값 없음 >> " + finalurl);
		// 3. 일반클래스에게 일을 시키는 작업
		String rdPath = com.execute(request, response);
		// 4. 페이지 응답 돌려주기
		request.getRequestDispatcher(rdPath).forward(request, response);
	}


}
