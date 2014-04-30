package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GongjiBean;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dao.GongjiDAO;

public class AdminGongjiOKAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		//서블릿으로 웹상에 출력되는 한글을 안깨지는 하는법
		//jsp월말평가 예상문제
		PrintWriter out=response.getWriter();
		//서블릿으로 출력스트림객체 만드는 법:jsp월말평가 예상
		HttpSession session=request.getSession();
		//jsp월말평가 문제예상:서블릿으로 세션객체 만드는 법
		request.setCharacterEncoding("UTF-8");
		//method=post방식일때 서버에서 받는 한글을 안깨지게 한다.
		//월말평가문제 예상
		//JSTL 커스텀태그 출력법:<c:out value =>jsp 예상문제
		String admin_id=(String)session.getAttribute("admin_id");

		if(admin_id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_login.html'");
			out.println("</script>");
			//웹표준에서 원하는 곳으로 이동하는 자바스크립트 객체
			//location='이동할 주소 또는 파일명';
			//웹표준 문제 예상.
			//jsp 에서 원하는 곳으로 이동:예상문제
			//response.sendRedirect("이동할 주소 또는 파일명");
		}else{
			String gongji_name=request.getParameter("gongji_name").trim();
			String gongji_title=request.getParameter("gongji_title").trim();
			String gongji_pwd=request.getParameter("gongji_pwd").trim();
			String gongji_cont=request.getParameter("gongji_cont").trim();

			GongjiBean gb=new GongjiBean();
			gb.setGongji_name(gongji_name); 
			gb.setGongji_title(gongji_title);
			gb.setGongji_pwd(gongji_pwd); gb.setGongji_cont(gongji_cont);

			GongjiDAO gd=new GongjiDAO();

			gd.insertAdminG(gb);//공지 저장

			ActionForward forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("admin_gongji_list.html");
			return forward; 
		}

		return null;
	}

}
