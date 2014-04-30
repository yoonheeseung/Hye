package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminGongjiWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		//세션객체를 생성
		
	String admin_id=(String)session.getAttribute("admin_id");
	//세션 키에 저장된 관리자 아이디를 구함.
	
	  int page=1;
	  if(request.getParameter("page") != null){
		  page=Integer.parseInt(request.getParameter("page"));
		  //get방식으로 넘어온 쪽번호를 정수형 숫자로 바꿔서 저장
	  }
	  if(admin_id == null){
	    	out.println("<script>");
	    	out.println("alert('다시 로그인 하세요!')");
	    	out.println("location='admin_login.html'");
	    	out.println("</script>");
	    }else{
	    request.setAttribute("page",page);	
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./jsp/admin/admin_gongji_write.jsp");
		return forward;
	    }
		return null;
	}

}
