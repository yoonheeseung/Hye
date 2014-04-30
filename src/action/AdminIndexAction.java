package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminIndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		//서블릿에서 출력스트림 만드는 법:월말평가
		HttpSession session=request.getSession();
		//세션객체를 생성
		String admin_id=(String)session.getAttribute("admin_id");
		//세션 아이디키로 반환되는 관리자 아이디는 자바 최상위
		//클래스 Object형으로 반환된다.그러므로 문자열 변수에 저장
		//하기 위해서는 강제 다운캐스팅을 해야 한다.
		if(admin_id == null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_login.html'");
			out.println("</script>");
		}else{
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./jsp/admin/admin_index.jsp");
			return forward;
		}
		
		return null;
	}

}
