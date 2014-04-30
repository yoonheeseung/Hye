package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GongjiBean;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dao.GongjiDAO;

public class AdminGongjiDELAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	    
		PrintWriter out=response.getWriter();
	    HttpSession session=request.getSession();
	   
		String admin_id=(String)session.getAttribute("admin_id");
		if(admin_id==null){
			out.println("<script>");
			out.println("alert('다시로그인하세요!')");
			out.println("history.go(-1)");
			out.println("</script>");
		}else{
			int gongji_no=Integer.parseInt(request.getParameter("gongji_no"));
			int page=1;
			if(request.getParameter("page")!=null){
				page=Integer.parseInt(request.getParameter("page"));
			}
			String del_pwd=request.getParameter("del_pwd");
			GongjiDAO gd=new GongjiDAO();
			GongjiBean db_pwd=gd.getGongjiCont(gongji_no);
			
			if(!db_pwd.getGongji_pwd().equals(del_pwd)){
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.back()");
				out.println("</script>");
			}else{
				gd.gongjiDEL(gongji_no);//삭제
				
				ActionForward forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("admin_gongji_list.html?page="+page);
				
				return forward;
			}
		}
		return null;
	}

}
