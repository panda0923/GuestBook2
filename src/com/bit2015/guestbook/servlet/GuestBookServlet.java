package com.bit2015.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bit2015.guest.web.action.GuestBookActionFactory;
import com.bit2015.guestbook.dao.GuestBookDao;
import com.bit2015.guestbook.vo.GuestbookVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

/**
 * Servlet implementation class GuestBookServlet
 */
@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request , response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		
		String a =request.getParameter("a");
		String actionName = request.getParameter( "a" );
		
		ActionFactory actionFactory = new GuestBookActionFactory();
		Action action = actionFactory.getAction( actionName );
		action.execute(request, response);
	}
}
//		
//		if("deleteform".equals(a)){
//			forwarding(request,response,"/view/deleteform.jsp");
//		}else if("delete".equals(a)){
//
//			String no = request.getParameter("no");
//			String password = request.getParameter("password");
//			
//			GuestbookVo vo = new GuestbookVo();
//			vo.setNo(Long.parseLong(no));
//			vo.setPassword(password);
//			
//			GuestBookDao dao =new  GuestBookDao();
//			dao.delete(vo);
//			
//			response.sendRedirect("/GuestBook2/gb");
//			
//		}else if("insert".equals(a)){
//			String name = request.getParameter( "name" );
//			String password = request.getParameter( "password" );
//			String message = request.getParameter( "message" );
//			
//			GuestbookVo vo = new GuestbookVo();
//			vo.setName(name);
//			vo.setPassword(password);
//			vo.setMessage(message);
//			
//			GuestBookDao dao = new GuestBookDao();
//			dao.insert(vo);
//			
//			response.sendRedirect( "/GuestBook2/gb" );//show로 페이지 이동시켜라
//		}
//		else{
//			GuestBookDao dao = new GuestBookDao();
//			List<GuestbookVo> list = dao.getList();
//			
//			request.setAttribute("List", list);
//			forwarding(request,response,"/view/index.jsp");
//		}	// TODO Auto-generated method stub
//	}
//	private void forwarding(HttpServletRequest request,HttpServletResponse response ,String path)throws ServletException,IOException{
//		RequestDispatcher requestdispatcher = request.getRequestDispatcher(path);
//		requestdispatcher.forward(request, response);
//	}
//}
