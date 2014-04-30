package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./jsp/admin/admin_login.jsp");
		return forward;
	}

}
