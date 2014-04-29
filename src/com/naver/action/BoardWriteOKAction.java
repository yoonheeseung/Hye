package com.naver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.naver.dao.BoardDAO;
import com.naver.model.BoardBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOKAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardDAO boarddao=new BoardDAO();
	   	BoardBean boarddata=new BoardBean();
	   	ActionForward forward=new ActionForward();
	   	
		String realFolder="";
   		String saveFolder="boardupload";
   		
   		int fileSize=10*1024*1024;
   		
   		realFolder=request.getRealPath(saveFolder);
   		
   		boolean result=false;

   		System.out.println("1");	
   			MultipartRequest multi=null;
   			
   			multi=new MultipartRequest(request,
   					realFolder,
   					fileSize,
   					"utf-8",
   					new DefaultFileRenamePolicy());
   			
   			boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
   			boarddata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
	   		boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
	   		boarddata.setBOARD_FILE(
	   				multi.getFilesystemName((String)multi.getFileNames().nextElement()));
	   		
	   		result=boarddao.boardWrite(boarddata);
	   		System.out.println("2");	
	   		if(result==false){
	   			System.out.println("3");
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		System.out.println("게시판 등록 완료");
	   		System.out.println("4");
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardList.bo");
	   		return forward;

	}

}
