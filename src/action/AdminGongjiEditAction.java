package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GongjiBean;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dao.GongjiDAO;

public class AdminGongjiEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		 PrintWriter out=response.getWriter();
		 HttpSession session=request.getSession();//로그인 인증용
		 
		 request.setCharacterEncoding("utf-8");;
		 //method=post방식일때 서버에서 받는 한글 안깨지게 함.
			
		 String admin_id=(String)session.getAttribute("admin_id");
		 //세션에서 관리자 아이디를 구함.
		 
		 if(admin_id==null){
			 out.println("<script>");
	    	   out.println("alert('다시 로그인 하세요!')");
	    	   out.println("location='admin_login.html'");
	    	   out.println("</script>");
		 }else{
			 int gongji_no=
					   Integer.parseInt(request.getParameter("gongji_no"));
					//히든으로 넘어온 공지번호를 정수형 숫자로 바꿔서 저장
			 int page=1;
			 if(request.getParameter("page") != null){
				  page=Integer.parseInt(request.getParameter("page"));	   
		 
		 }
		 
			 String gongji_name=request.getParameter("gongji_name").trim();
			 String gongji_title=request.getParameter("gongji_title").trim();
			 String gongji_pwd=request.getParameter("gongj_pwd");
			 String gongji_cont=request.getParameter("gongji_cont");
			 
			 GongjiDAO gd=new GongjiDAO();
			 GongjiBean db_pwd=gd.getGongjiCont(gongji_no);
			 
			 if(db_pwd.getGongji_pwd().equals(gongji_pwd)){
				 GongjiBean g=new GongjiBean();
				 g.setGongji_no(gongji_no);
				 g.setGongji_name(gongji_name);
				 g.setGongji_title(gongji_title);
				 g.setGongji_cont(gongji_cont);
				 
				 gd.gongjiEdit(g);//수정메서드 호출
				 
				 ActionForward forward=new ActionForward();
				 forward.setRedirect(true);
				 forward.setPath("admin_gongji_lsit.html?page="+page);
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
