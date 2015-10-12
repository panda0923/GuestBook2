package com.bit2015.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.guestbook.dao.GuestBookDao;
import com.bit2015.guestbook.vo.GuestbookVo;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter( "name" );
		String password = request.getParameter( "password" );
		String message = request.getParameter( "message" );
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		GuestBookDao dao = new GuestBookDao();
		dao.insert(vo);
		
		response.sendRedirect( "/GuestBook2/gb" );//show로 페이지 이동시켜라

	}

}
