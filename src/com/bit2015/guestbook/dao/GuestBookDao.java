package com.bit2015.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.guestbook.vo.GuestbookVo;

public class GuestBookDao {
	private Connection getConnection() throws SQLException {
		
		Connection connection = null;
		
		try {
			//1.드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2.커넥션 만들기(ORACLE DB)
			String dbURL  = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "webdb", "webdb" );
			
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		} 
		
		return connection;
	}
	
	public Boolean delete( GuestbookVo vo ) {
		int countDeleted = 0;
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql = 
				" delete" +
				"   from guestbook" + 
				"  where no=? and password=?";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setLong( 1, vo.getNo() );
			pstmt.setString( 2, vo.getPassword() );
			
			//4. query 실행
			countDeleted = pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}
		
		return ( countDeleted == 1 );
	}
	
	public Long insert( GuestbookVo vo ) {
		Long no=-1L;
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			System.out.println(vo.toString());
			//2. Statement 준비
			String sql = 
				" insert" +
				"   into guestbook" + 
				" values( ?, ?, ?,SYSDATE,guestbook_seq.nextval )";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			 
			//3. binding
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getPassword() );
			pstmt.setString( 3, vo.getMessage() );
			
			//4. query 실행
			System.out.println("here");
			pstmt.executeUpdate();
			System.out.println("here2");
			//5. 자원정리
			pstmt.close();
			//6시퀀스가져오기
			
			Statement stmt = connection.createStatement();
			ResultSet rs =stmt.executeQuery("select guestbook_seq.currval from dual");
			if(rs.next()){
				no = rs.getLong(1);
			}
			rs.close();
			stmt.close();
			
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}
		return no;
	}
	
	public GuestbookVo get(Long no){
		GuestbookVo vo = null;
		ResultSet rs = null;
		
		try{
			Connection conn =getConnection();
			String sql="select name,password,message,reg_date from guestbook where no =? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				vo=new GuestbookVo();
				String name = rs.getString(1);
				String password = rs.getString(2);
				String message = rs.getString(3);
				String reg_date = rs.getString(4);
				
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				vo.setReg_date(reg_date);
				
				
			}
		}catch(Exception ex){
			System.out.println( "SQL 오류-" + ex );
		}
		
		System.out.print(vo);
		return vo;
	}
	public List<GuestbookVo> getList(int page) {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		try {
			//1. 커넥션 만들기(ORACLE DB)
			Connection connection = getConnection();
			//3. SQL문 실행
			String sql =
					"select  rownum as r,A.* "
					+"from (select "
					       +"no, " 
					       +"name,  "     
					       +"to_char(reg_date,'yyyy-mm-dd hh:mi:ss'), "
					       +"message "
					       +"from GUESTBOOK "
					       +"order by reg_date desc) A "
					  +"where 1<=r and r<=5";
			//2. Statement 생성
			PreparedStatement pstmt = connection.prepareStatement(sql);
			//3.바인딩
			pstmt.setInt(1, (page-1)*5+1);
			pstmt.setInt(2, (page*5));

			
			//sql문실행
		
			ResultSet rs = pstmt.executeQuery();
			
			//4. row 가져오기
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String message = rs.getString( 4 );
				String regDate = rs.getString( 5 );
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo( no );
				vo.setName( name );
				// vo.setPassword(password);
				vo.setMessage( message );
				vo.setReg_date( regDate );
				
				list.add( vo );
			}
			
			//6. 자원 정리
			rs.close();
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}
		
		return list;
	}
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		try {
			//1. 커넥션 만들기(ORACLE DB)
			Connection connection = getConnection();
			
			//2. Statement 생성
			Statement stmt = connection.createStatement();
			
			//3. SQL문 실행
			String sql =
				"   select no,"
			  + "          name,"
			  + "          message,"
			  + "          to_char( reg_date, 'yyyy-MM-dd hh:mm:ss' )"
			  + "     from guestbook"
			  + " order by reg_date desc";
			ResultSet rs = stmt.executeQuery( sql );
			
			//4. row 가져오기
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String message = rs.getString( 3 );
				String regDate = rs.getString( 4 );
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo( no );
				vo.setName( name );
				// vo.setPassword(password);
				vo.setMessage( message );
				vo.setReg_date( regDate );
				
				list.add( vo );
			}
			
			//6. 자원 정리
			rs.close();
			stmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}
		
		return list;
	}
}