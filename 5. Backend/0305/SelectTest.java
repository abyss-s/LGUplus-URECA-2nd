package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTest {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
//			DB 접속하는 클래스를 메모리에 로딩하기
			Class.forName("com.mysql.cj.jdbc.Driver");
			
//			DB에 접속하기    접속할 url, 계정명, 계정비밀번호
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uereka?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8&useSSL=false","uereka","uereka");
			
//			쿼리 작성하기  
			String sql = "select * from emp ";
			
//			쿼리를 DB서버에 보내기 위한 Statement 만들기
			stmt = con.prepareStatement(sql);
			
//			쿼리 실행하기 
			rs = stmt.executeQuery();
			System.out.println("사원번호\t이름\t급여\t부서번호\t업무");
			
//			결과 조회하기
			while(rs.next()) {
				System.out.print(rs.getInt("empno"));
				System.out.print("\t");
				System.out.print(rs.getString("ename"));
				System.out.print("\t");
				System.out.print(rs.getInt("sal"));
				System.out.print("\t");
				System.out.print(rs.getInt("deptno"));
				System.out.print("\t");
				System.out.print(rs.getString("job"));
				System.out.println("\t");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			서버와 연결 끊기
			if(rs!=null)	try {rs.close();}catch(Exception e1) {}
			if(stmt!=null)	try {stmt.close();}catch(Exception e1) {}
			if(con!=null)	try {con.close();}catch(Exception e1) {}
		}
	}
}
