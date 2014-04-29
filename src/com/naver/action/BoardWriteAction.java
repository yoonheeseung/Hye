package com.naver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page=1;
		if(request.getParameter("page") !=null){
			//get방식으로 넘어오는 페이지 번호값이 있는 경우
			page=Integer.parseInt(request.getParameter("page"));
			//정수형 숫자로 바꿔서 page변수에 저장
		}
		
		request.setAttribute("page", page);
		//page키에 쪽번호 저장
		ActionForward forward=new ActionForward();
		forward.setPath("./board/board_write.jsp");
		//jsp/board폴더의 board_write.jsp 뷰페이지가 실행된다.
		forward.setRedirect(false);
		return forward;
	}

}
