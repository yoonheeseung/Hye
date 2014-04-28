package com.naver.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.naver.dao.BoardDAO;
import com.naver.model.BoardBean;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BoardDAO boarddao=new BoardDAO();
		
		BoardBean boardb = new BoardBean();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = boarddao.getListCount();//총 리스트 수를 받아옴
		List<BoardBean> boardlist =  boarddao.getBoardList(page,limit);//리스트를 받아옴
		
		//총페이지수
		int maxpage = (int) (listcount/limit+0.95);//0.95를 더해서 올림 처리
		//현재 페이지에 보여줄 시작 페이지수
		int startpage=(((int) ((double)page/10+0.9)) -1)*10+1;
		//현재 페이지에 보여줄 마지막 페이지 수
		int endpage=maxpage;
		
		if(endpage>startpage+10-1) endpage=startpage+10-1;
		
		request.setAttribute("page", page); // 현재 페이지 수.
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수.
		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수.
		request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수.
		request.setAttribute("listcount", listcount); // 글 수.
		request.setAttribute("boardlist", boardlist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);// forward에서 분기되므로 키값유지
		forward.setPath("./board/board_list.jsp");
	
		return forward;
	
	}

}
