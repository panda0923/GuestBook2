package com.bit2015.guestbook.dao;

import java.util.List;

import com.bit2015.guestbook.vo.GuestbookVo;
import com.bit2015.guestbook.dao.GuestBookDao;;
public class test {
	public static void main(String[] args) {
		getListtest();
	}
	
	public static void getListtest(){
		GuestBookDao dao = new GuestBookDao();
		List<GuestbookVo> list = dao.getList();

		
		
		for(GuestbookVo vo :list){
			System.out.println(vo);
		}
	}
}