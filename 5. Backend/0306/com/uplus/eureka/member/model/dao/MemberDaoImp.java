package com.uplus.eureka.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.uplus.eureka.member.model.dto.Member;
import com.uplus.eureka.util.DBUtil;

public class MemberDaoImp implements MemberDao {
	
	private DataSource ds;				//@Autowired 안 붙여도 됨.
	private DBUtil util;
	
	/**클래스 내에 생성자가 한개 있으면 해당 생성자로 객체를 생성하면서 인자로 선언한 객체를 자동 주입한다.  */
	public MemberDaoImp(DataSource ds, DBUtil util) {
		super();
		this.ds = ds;
		this.util = util;
	}

	public Member search(String id) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String sql = " select * from members where id = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				Member member =  new Member( ); 
				member.setId(rs.getString("id")); 
				member.setPassword(rs.getString("password")); 
				member.setName(rs.getString("name"));
			   return member;	
			}
		} finally {
			util.close(rs, stmt, con);
		}
		return null;
	}
	@Override
	public void regist(Member user) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			String sql = " insert into members (id, name, password) values(?,?,?) ";
			stmt = con.prepareStatement(sql);
			int idx=1;
			stmt.setString(idx++, user.getId());
			stmt.setString(idx++, user.getName());
			stmt.setString(idx++, user.getPassword());
			stmt.executeUpdate();
		} finally {
			util.close(stmt, con);
		}
	}

	@Override
	public void update(Member user) throws SQLException {

	}

	@Override
	public void remove(Member id) throws SQLException {

	}

}
