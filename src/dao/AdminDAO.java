package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.AdminBean;

public class AdminDAO {

	Connection con=null; //데이터 베이스 연결 레퍼런스
	PreparedStatement pstmt=null; //쿼리문 실행 레퍼런스
	ResultSet rs=null; //쿼리문 실행 결과 레코드를 저장할 레퍼런스
	DataSource ds=null; //커넥션 풀 레퍼런스
	String sql=null; //쿼리문 저장변수
	
	public AdminDAO(){
		try{
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/xe");
			//커넥션 풀 ds 객체 생성
		}catch(Exception e){
			e.printStackTrace();
		}
	}//생성자

	/*관리자 아이디 검색 */
	public AdminBean adminID(String admin_id) {
		AdminBean db_id=null;
		try {
			con=ds.getConnection();
			sql="select * from admin7 where admin_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,admin_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				db_id=new AdminBean();
				db_id.setAdmin_id(rs.getString("admin_id"));
				db_id.setAdmin_pwd(rs.getString("admin_pwd"));
				db_id.setAdmin_name(rs.getString("admin_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db_id;
	}//adminID()

	
}
