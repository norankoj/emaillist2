package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmaillistDAO;
import com.javaex.util.Webutil;
import com.javaex.vo.EmailVO;


@WebServlet("/el") //주소창에 치는 친구 
public class EmaillistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		System.out.println("controller");
		String actionName=request.getParameter("a");
		
		//actionName.equals("form")라고 쓰면 actionName 값이 없을땐 exception 발생
		//그래서 안전하게 반대로 쓴다.
		
		if("form".equals(actionName)) {
			//form
			
			//static 으로 했기에 이렇게 쓸 수 있다.
			Webutil.forword(request, response, "/WEB-INF/form.jsp");
			
			//RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/form.jsp");
			//rs.forward(request, response); //내가 받은 요청과 응답값을 jsp파일로 보내준다
			//jsp는 아무것도 모르니까 옆 담당자에게 넘긴 것이다.
			
			
		}else if ("insert".equals(actionName)) {
			//insert
			
			String last_name = request.getParameter("ln");
			String first_name = request.getParameter("fn");
			String email = request.getParameter("email");
			
			EmailVO vo = new EmailVO(last_name,first_name,email);
			
			EmaillistDAO dao = new EmaillistDAO();
			dao.insert(vo);

			//리스트 리다이렉트 
			//사용자가 받은다음 다시 들어가는 거니까 이땐 url을 쓴다.
			//response.sendRedirect("/Emaillist2/el");
			
			Webutil.redirect(request, response, "/Emaillist2/el");
			
			
		}else {
			//list
			
			EmaillistDAO dao = new EmaillistDAO();
			ArrayList<EmailVO> list = dao.getList();
			
			//중간에 우리가 계산한 데이터를 보낼때, 파라미터로 보낼 수 가 없다.
			//이럴때 쓰는게 attribute (가공한 데이터 보낼때)
			
			request.setAttribute("list", list);
			
			Webutil.forword(request, response, "/WEB-INF/list.jsp");
		}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
