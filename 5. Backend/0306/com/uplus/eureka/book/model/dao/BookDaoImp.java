package com.uplus.eureka.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.uplus.eureka.book.model.dto.Book;
import com.uplus.eureka.book.model.dto.PageBean;
import com.uplus.eureka.util.DBUtil;

public class BookDaoImp implements BookDao {
	
	private DataSource ds;
	private DBUtil dbutil;

	//@Autowired
	/** 클래스에 인자 있는 생성자가 한개 있는 경우  Spring이 인자 있는 생성자로  자동 주입하기 
	 *  때문에 Autowired를 생략 해도 된다. */
	public BookDaoImp(DataSource ds, DBUtil dbutil) {
		super();
		this.ds = ds;
		this.dbutil = dbutil;
	}

	@Override
	public List<Book> searchAll(PageBean bean) throws SQLException {
		Connection con= null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			StringBuilder sb = new StringBuilder(50);
			sb.append(" select * from book where 1=1 ");
			
			String key = bean.getKey();
			String word= bean.getWord();
			int start  = bean.getStart();
			int end    = bean.getInterval();
			
			if( key!= null && !key.equals("all") && word!=null && !word.trim().equals("")) {
				if(key.equals("isbn")) {
					sb.append(" and isbn =? ");
				}else if(key.equals("title")) {
					sb.append(" and title like ?  ");
				}else if(key.equals("author")) {
					sb.append(" and author like ?  ");
				}
			}
			
			sb.append(" limit ? , ? ");
			stmt = con.prepareStatement(sb.toString());
			
			int idx = 1;
			if( key!= null && !key.equals("all") && word!=null && !word.trim().equals("")) {
				if(key.equals("isbn")) {
					stmt.setString(idx++, word);
				}else {
					stmt.setString(idx++, "%"+word+"%");
				}
			}
			
			stmt.setInt(idx++,start);
			stmt.setInt(idx++,end);
			rs = stmt.executeQuery();
			ArrayList<Book> books = new ArrayList<Book>(end);
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				books.add(book);
			}
			return books;
		} finally {
			dbutil.close(rs, stmt, con);
		}
	}

	@Override
	public int totalCount(PageBean bean) throws SQLException {
		Connection con= null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			StringBuilder sb = new StringBuilder(50);
			sb.append(" select count(*) as cnt from book where 1=1");
			
			String key = bean.getKey();
			String word= bean.getWord();
			if( key!= null && !key.equals("all") && word!=null && !word.trim().equals("")) {
				if(key.equals("isbn")) {
					sb.append(" and isbn =? ");
				}else if(key.equals("title")) {
					sb.append(" and title like ?  ");
				}else if(key.equals("author")) {
					sb.append(" and author like ?  ");
				}
			}
			stmt = con.prepareStatement(sb.toString());
			
			int idx = 1;
			if( key!= null && !key.equals("all") && word!=null && !word.trim().equals("")) {
				if(key.equals("isbn")) {
					stmt.setString(idx++, word);
				}else {
					stmt.setString(idx++, "%"+word+"%");
				}
			}
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return 0;
	}

	@Override
	public Book search(String isbn) throws SQLException {
		Connection con= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String sql = " select * from book where isbn =? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, isbn);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setDescrib(rs.getString("describ"));
				return book;
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}	
		return null;
	}

	@Override
	public void remove(String isbn) throws SQLException {
		Connection con =null;
		PreparedStatement stmt = null;
		try {
			String sql = "delete from book where isbn=? ";
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, isbn);
			stmt.executeUpdate();
		} finally {
			dbutil.close(stmt, con);
		}

	}

	@Override
	public void update(Book book) throws SQLException {
		Connection con =null;
		PreparedStatement stmt = null;
		try {
			String sql = " update book set title=?,author=?,price=?,describ=? where isbn=?";
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			
			stmt.setString( 1 , book.getTitle());
			stmt.setString( 2 , book.getAuthor());
			stmt.setInt( 3 , book.getPrice());
			stmt.setString( 4 , book.getDescrib());
			stmt.setString( 5 , book.getIsbn());
			
			stmt.executeUpdate();
		} finally {
			dbutil.close(stmt, con);
		}

	}

	@Override
	public void insert(Book book) throws SQLException {
		Connection con =null;
		PreparedStatement stmt = null;
		try {
			String sql = " insert into book(isbn,title,author,price,describ) values(?,?,?,?,?)";
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			
			int idx = 1;
			stmt.setString( idx++ , book.getIsbn());
			stmt.setString( idx++ , book.getTitle());
			stmt.setString( idx++ , book.getAuthor());
			stmt.setInt( idx++ , book.getPrice());
			stmt.setString( idx++ , book.getDescrib());
			stmt.executeUpdate();
		} finally {
			dbutil.close(stmt, con);
		}

	}

}
