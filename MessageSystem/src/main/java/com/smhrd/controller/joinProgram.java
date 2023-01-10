package com.smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.model.MessageDAO;
import com.smhrd.model.MessageDTO;

@WebServlet("/joinProgram")
public class joinProgram extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 1. 한글 인코딩 잡아주기
		request.setCharacterEncoding("UTF-8");
		// 2. 요청받은 데이터 꺼내오기
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		// 3. 하나의 자료형으로 묶어주기
		MessageDTO dto = new MessageDTO(email, pw, phone, address);
		// 4. database에 데이터를 insert하는 작업
		// 4-1. DAO생성하기
		MessageDAO dao = new MessageDAO();
		// 4-2. DAO안에 있는 메소드 사용하기
		int row = dao.join(dto);
		// 5. 로그인 성공했을 때 join_success.jsp로 이동
		// 단, request영역에 사용자의 email을 담아서 forward방식으로 이동
		if(row > 0) {
			response.sendRedirect("join_success.jsp");
		}else {
		// 6. 그렇지 않은경우 main.jsp로 redirect방식으로 이동
			response.sendRedirect("main.jsp");
		}
		
		
		
		
	}

}
