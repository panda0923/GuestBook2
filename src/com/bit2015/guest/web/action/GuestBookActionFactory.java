package com.bit2015.guest.web.action;

import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;
import com.bit2015.web.action.AjaxIndexAction;
import com.bit2015.web.action.DeleteAction;
import com.bit2015.web.action.DeleteFormAction;
import com.bit2015.web.action.IndexAction;
import com.bit2015.web.action.InsertAction;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		//actionName에 들어오는 값에 따라 리턴값이 달라져야한다.
		//		-->방법:getAction앞을 Action으로 한 후
		//			Action을  new로 구현한다.(Ex:action = new FormAction)
		//Action.java를 부모 클래스(인터페이스)로 한후 자식 클래스(formAction.java)에 오버라이딩한다. 
		Action action =null;
		if("deleteform".equals(actionName)){
			action= new DeleteFormAction();
		}else if("delete".equals(actionName)){
			action =new DeleteAction();
		}else if("insert".equals(actionName)){
			action = new InsertAction();
		}else if("ajax".equals(actionName)){
			action = new AjaxIndexAction();
		}
		else{
			//dafault인 경우
			action = new IndexAction();
		}
		
		return action;
	}

}
