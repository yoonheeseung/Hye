package com.naver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.naver.dao.BoardDAO;
import com.naver.model.BoardBean;

public class BoardDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			// get방식으로 넘어온 글번호를 정수형 숫자로 바꿔서 저장
		}
		
		int board_no = Integer.parseInt(request.getParameter("num"));
		
		String status = request.getParameter("state");
		
		BoardDAO boarddao=new BoardDAO();
	   	BoardBean bcont=new BoardBean();
	   	
		int num=Integer.parseInt(request.getParameter("num"));
		if(status.equals("cont")){
			boarddao.setReadCountUpdate(num);
		}
	
		bcont=boarddao.getDetail(num);
	   	String board_cont = bcont.getBOARD_CONTENT().replace("\n", "<br/>");

	   	request.setAttribute("bcont", bcont);
		request.setAttribute("page", page);//이전페이지 쪽번호로 가기위한 페이지번호 저장
		
	   	
	   	if(bcont==null){
	   		System.out.println("상세보기 실패");
	   		return null;
	   	}
	   	System.out.println("상세보기 성공");
	   	
	   	
	   	ActionForward forward = new ActionForward();
	   	forward.setRedirect(false);
	   	System.out.println("status: "+status);
	   	if (status.equals("cont")) {
			request.setAttribute("board_cont", board_cont);
			forward.setPath("./board/board_view.jsp");
		} else if (status.equals("reply")) {
			System.out.println("reply");
			request.setAttribute("board_cont", board_cont);
			forward.setPath("./board/board_reply.jsp");
		} else if (status.equals("edit")) {
			forward.setPath("./board/board_modify.jsp");
		} else if (status.equals("del")) {
			forward.setPath("./board/board_delete.jsp");
		}

   		return forward;
	}

}
