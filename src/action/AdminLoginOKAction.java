package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminBean;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dao.AdminDAO;

public class AdminLoginOKAction implements Action {

	HttpSession session;
	String admin_pwd;

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out=response.getWriter();
	session = request.getSession();
	//로그인 인증을 처리하기 위해 세션객체 생성
	request.setCharacterEncoding("utf-8");
	//mtthod=post방식으로 넘어온 한글을 안 깨지게 하기.
	
	String admin_id=request.getParameter("admin_id").trim();
	admin_pwd = request.getParameter("admin_pwd").trim();
	
	AdminDAO ad=new AdminDAO();
	
	AdminBean db_id=ad.adminID(admin_id);
	
	if(db_id==null){
		out.println("<script>");
		out.println("alert('관리자가 아닙니다!')");
		out.println("hsitory.go(-1)");
		out.println("</script>");
	}else{
		if(db_id.getAdmin_pwd().equals(admin_pwd)){
			session.setAttribute("admin_id",db_id.getAdmin_id());
			session.setAttribute("admin_name",db_id.getAdmin_name());
			
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("admin_index.html");
			return forward;
			}else{
			  out.println("<script>");
			  out.println("alert('비번이 다릅니다!')");
			  out.println("history.go(-1)");
			  out.println("</script>");
			}		
		}
		return null;
	}

}
