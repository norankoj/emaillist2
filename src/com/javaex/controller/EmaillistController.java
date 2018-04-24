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


@WebServlet("/el") //�ּ�â�� ġ�� ģ�� 
public class EmaillistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		System.out.println("controller");
		String actionName=request.getParameter("a");
		
		//actionName.equals("form")��� ���� actionName ���� ������ exception �߻�
		//�׷��� �����ϰ� �ݴ�� ����.
		
		if("form".equals(actionName)) {
			//form
			
			//static ���� �߱⿡ �̷��� �� �� �ִ�.
			Webutil.forword(request, response, "/WEB-INF/form.jsp");
			
			//RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/form.jsp");
			//rs.forward(request, response); //���� ���� ��û�� ���䰪�� jsp���Ϸ� �����ش�
			//jsp�� �ƹ��͵� �𸣴ϱ� �� ����ڿ��� �ѱ� ���̴�.
			
			
		}else if ("insert".equals(actionName)) {
			//insert
			
			String last_name = request.getParameter("ln");
			String first_name = request.getParameter("fn");
			String email = request.getParameter("email");
			
			EmailVO vo = new EmailVO(last_name,first_name,email);
			
			EmaillistDAO dao = new EmaillistDAO();
			dao.insert(vo);

			//����Ʈ �����̷�Ʈ 
			//����ڰ� �������� �ٽ� ���� �Ŵϱ� �̶� url�� ����.
			//response.sendRedirect("/Emaillist2/el");
			
			Webutil.redirect(request, response, "/Emaillist2/el");
			
			
		}else {
			//list
			
			EmaillistDAO dao = new EmaillistDAO();
			ArrayList<EmailVO> list = dao.getList();
			
			//�߰��� �츮�� ����� �����͸� ������, �Ķ���ͷ� ���� �� �� ����.
			//�̷��� ���°� attribute (������ ������ ������)
			
			request.setAttribute("list", list);
			
			Webutil.forword(request, response, "/WEB-INF/list.jsp");
		}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
