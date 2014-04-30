package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GongjiBean;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dao.GongjiDAO;

public class AdminGongjiListAction implements Action {

	GongjiDAO boarddao=new GongjiDAO();
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	int page=1;
	int limit=7;
	
	if(request.getParameter("page")!=null){
		page=Integer.parseInt(request.getParameter("page"));
	}
	
	int listcount = boarddao.getListCount();
	List<GongjiBean> glist=boarddao.getGongjiList(page,limit);
		
	//총페이지 수
	int maxpage=(int)((double)listcount/limit+0.95);
	//현재 페이지에 보여줄 시작 페이지 수(1,11,21,...)
	int startpage=(((int) ((double)page / 10 + 0.9)) -1)*10+1;
	//현재 페이지에 보여줄 마지막 페이지 수(10,20,30...)
	int endpage=maxpage;
	
	if(endpage > startpage + 10 -1)
		endpage = startpage + 10 -1;
	request.setAttribute("page", page);
	request.setAttribute("maxpage", maxpage);//최대페이지
	request.setAttribute("startpage", startpage);//현재페이지에 표시할 첫페이지
	request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수.
	request.setAttribute("listcount", listcount); // 글 수.
	request.setAttribute("glist", glist);
	
	ActionForward forward=new ActionForward();
	forward.setRedirect(false);
	forward.setPath("./jsp/admin/admin_gongji_list.jsp");
		
		return forward;
	}

}
