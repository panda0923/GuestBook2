package com.bit2015.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.guestbook.dao.GuestBookDao;
import com.bit2015.guestbook.vo.GuestbookVo;
import com.bit2015.web.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		GuestBookDao dao = new GuestBookDao();
		List<GuestbookVo> list = dao.getList();
		
		request.setAttribute("List", list);
		WebUtil.forwarding(request,response,"/view/index.jsp");

	}

}
