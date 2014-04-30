package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminLogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		response.setContentType("text/html;charset=UTF-8");
	    //서블릿에서 웹브라우저에 출력되는 한글을 안깨지게 함.
	    //월말평가 문제 출제 가능성.
	    PrintWriter out=response.getWriter();
	    //출력스트림객체 생성(월말평가 출제 가능성)
	    HttpSession session=request.getSession();
	    //로그인 인증할때 세션객체를 만드는 법(월말평가 출제가능성)
	    
	    session.invalidate();//세션 만료.만료하면 세션아이디 값을
	    //잃어 버린다.
	    
	    out.println("<script>");
	    out.println("alert('로그아웃 되었습니다!')");
	    out.println("location='admin_login.html'");
	    out.println("</script>");
	    
		return null;
	}

}
