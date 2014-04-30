package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GongjiBean;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dao.GongjiDAO;

public class AdminGongjiContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out=response.getWriter();
	    HttpSession session=request.getSession();
	    
   String admin_id=(String)session.getAttribute("admin_id");
int gongji_no=
    Integer.parseInt(request.getParameter("gongji_no"));
int page=1;
    if(request.getParameter("page") != null){
    	page=Integer.parseInt(request.getParameter("page"));
    }
    String state=request.getParameter("state");
    
    if(admin_id == null){
    	out.println("<script>");
    	out.println("alert('다시 로그인 하세요!')");
    	out.println("location='admin_login.html'");
    	out.println("</script>");
    }else{
    	GongjiDAO gd=new GongjiDAO();
    	GongjiBean gb=gd.getGongjiCont(gongji_no);
    	//번호에 해당하는 공지 내용을 디비로 부터 가져온다.
String gongji_cont=gb.getGongji_cont().replace("\n","<br/>");
//textarea박스에서 엔터키 친 부분을 웹상에 보일때 다음줄로 개행해서
//보여준다.

       request.setAttribute("gcont",gb);
       request.setAttribute("page",page);
       
       ActionForward forward=new ActionForward();
       forward.setRedirect(false);
       
       if(state.equals("cont")){//공지내용
    	   request.setAttribute("gongji_cont",gongji_cont);
    	   forward.setPath("./jsp/admin/admin_gongji_cont.jsp");
       }else if(state.equals("edit")){//수정폼
    	   forward.setPath("./jsp/admin/admin_gongji_edit.jsp");
       }else if(state.equals("del")){//삭제폼
    	   forward.setPath("./jsp/admin/admin_gongji_del.jsp");
       }
       return forward;
    }
		return null;
	}
}




