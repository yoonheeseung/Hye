package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.GongjiBean;

public class GongjiDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	String sql = null;

	public GongjiDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xe");
			// JNDI 커넥션 풀 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// 생성자

	public int getListCount() {
		int count = 0;
		try {
			con = ds.getConnection();
			sql = "select count(*) from gongji7";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				// 1의 뜻은 select문 다음에 나오는 첫번째 필드명을
				// 가리킨다.정수형 숫자 레코드값을 반환할때는
				// getInt()메서드를 사용한다.
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	public List<GongjiBean> getGongjiList(int page, int limit) {
		List<GongjiBean> list = new ArrayList<GongjiBean>();
		int startrow = (page - 1) * 7 + 1;// 읽기 시작할 row번호
		int endrow = startrow + limit - 1;// 읽을 마지막 row번호.
		try {
			con = ds.getConnection();
			sql = "select * from "
					+ "(select rownum rnum,gongji_no,gongji_name,gongji_title,"
					+ "gongji_hit,gongji_date "
					+ " from(select * from gongji7 order by gongji_no desc))"
					+ " where rnum > ? and rnum <= ?";
			// 월말평가예상(order by gongji_no desc):내림차순 정렬
			// 번호를 내림차순 정렬하면 큰숫자가 먼저 정렬
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GongjiBean g = new GongjiBean();
				g.setGongji_no(rs.getInt("gongji_no"));
				g.setGongji_name(rs.getString("gongji_name"));
				g.setGongji_title(rs.getString("gongji_title"));
				g.setGongji_hit(rs.getInt("gongji_hit"));
				g.setGongji_date(rs.getString("gongji_date"));

				list.add(g);// 컬렉션에 추가

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}// getGongjiList()

	// 공지내용+수정+삭제폼
	public GongjiBean getGongjiCont(int gongji_no) {
		GongjiBean g = null;
		try {
			con = ds.getConnection();
			sql = "select * from gongji7 where gongji_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gongji_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				g = new GongjiBean();
				g.setGongji_no(rs.getInt("gongji_no"));
				g.setGongji_name(rs.getString("gongji_name"));
				g.setGongji_title(rs.getString("gongji_title"));
				g.setGongji_cont(rs.getString("gongji_cont"));
				g.setGongji_hit(rs.getInt("gongji_hit"));
				g.setGongji_pwd(rs.getString("gongji_pwd"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}// getGongjiCont()

	/* 관리자 공지 수정 */
	public void gongjiEdit(GongjiBean g) {
		try {
			con = ds.getConnection();
			sql = "update gognji7 "
					+ "set gongji_name=?, gongji_title=?,gognji_cont=? "
					+ " where gongji_no=?";
			pstmt.setString(1, g.getGongji_name());
			pstmt.setString(2, g.getGongji_title());
			pstmt.setString(3, g.getGongji_cont());
			pstmt.setInt(4, g.getGongji_no());

			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insertAdminG(GongjiBean gb) {
		try {
			con=ds.getConnection();
			sql="insert into gongji7 (gongji_no,gongji_name,"
					+"gongji_title,gongji_pwd,gongji_cont,gongji_date) values("
					+"gongji7_no_seq.nextval,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);//쿼리문 실행 객체
			//생성
			pstmt.setString(1,gb.getGongji_name());
			pstmt.setString(2,gb.getGongji_title());
			pstmt.setString(3,gb.getGongji_pwd());
			pstmt.setString(4,gb.getGongji_cont());
			
			pstmt.executeUpdate();//저장 쿼리문 실행		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void gongjiDEL(int gongji_no) {
		try {
			con=ds.getConnection();
			sql="delete gongji7 where gongji_no=?";
			pstmt=con.prepareStatement(sql);//쿼리문 실행 객체
			pstmt.setInt(1,gongji_no);
			pstmt.executeUpdate();//저장 쿼리문 실행		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
