package com.naver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.naver.model.BoardBean;


public class BoardDAO {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	DataSource ds=null;
	String sql=null;
	Context ctx;
	
	public BoardDAO() {
		try {
			 ctx = new InitialContext();
			 ds =(DataSource) ctx.lookup("java:comp/env/jdbc/xe");
			 con= ds.getConnection();
		} catch (Exception e) {
			System.out.println("DB연결 실패:"+e);
			return;
		}
	}

	public int getListCount() {
		int liCnt=0;
		sql="select count(*) from board";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				liCnt=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return liCnt;
	}

	//글목록보기
	public List<BoardBean> getBoardList(int page, int limit) {
		List<BoardBean> blist =new ArrayList<BoardBean>();
		try {
			
			String board_list_sql="select * from (select rownum rnum,Board_num,board_name,"
					                           + " board_subject,board_content,board_file,board_re_ref,board_re_lev,"
					                           + " board_re_seq,board_readcount,board_date "
					                           + " from (select * from board order by board_re_ref desc, board_re_seq asc))"
					                           + " where rnum>=? and rnum<=?";
			
			int startrow=(page-1)*10-1; //읽기시작할 row번호
			int endrow=startrow+limit-1;//읽을 마지막 row번호
			
			pstmt=con.prepareStatement(board_list_sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs=pstmt.executeQuery();
			
			
			while(rs.next()){
			   BoardBean b=new BoardBean();
			  
			    b.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				b.setBOARD_NAME(rs.getString("BOARD_NAME"));
				b.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				b.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				b.setBOARD_FILE(rs.getString("BOARD_FILE"));
				b.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				b.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				b.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				b.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				b.setBOARD_DATE(rs.getString("BOARD_DATE"));
				
				blist.add(b);
			}	
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return blist;
	}

	public boolean boardWrite(BoardBean boarddata) {
		int result=0;
		
		try{
			sql="select max(board_num) from board";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())

			sql="insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT, "
					+ "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,"
					+ "BOARD_READCOUNT,BOARD_DATE) "
					+ "values(HYBOARD_NO_SEQ.nextval,?,?,?,?,?,HYBOARD_NO_SEQ.nextval,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, boarddata.getBOARD_NAME());
			pstmt.setString(2, boarddata.getBOARD_PASS());
			pstmt.setString(3, boarddata.getBOARD_SUBJECT());
			pstmt.setString(4, boarddata.getBOARD_CONTENT());
			pstmt.setString(5, boarddata.getBOARD_FILE());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			
			result=pstmt.executeUpdate();
			
			if(result==0)return false;
			
			return true;
			
		}catch(Exception ex){
			System.out.println("boardWrite 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}  
		return false;
	}
	
	//조회수 증가
	public void setReadCountUpdate(int num) {
		String sql="update board set BOARD_READCOUNT = BOARD_READCOUNT+1 "
				 + "where BOARD_NUM =? ";
			try{
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}catch(SQLException ex){
				System.out.println("setReadCountUpdate 에러 : "+ex);
			}
			finally{
				try{
				if(pstmt!=null)pstmt.close();
				if(con!=null) con.close();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
		}
		
	}
	
//글내용 
	public BoardBean getDetail(int num) {
		BoardBean board = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * from board where BOARD_NUM = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getString("BOARD_DATE"));
			}
			return board;
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return null;
	}

}
