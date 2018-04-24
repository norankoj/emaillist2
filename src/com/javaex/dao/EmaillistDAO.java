package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javaex.vo.EmailVO;

public class EmaillistDAO {
	
	public void insert(EmailVO vo) {
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		
		Connection con =null;
		PreparedStatement pstmt = null;		
		int count;
		try {
			//1.드라이버로딩
			Class.forName(driver);
			
			//2.connection얻어오기 
			con = DriverManager.getConnection(url,"webdb","webdb");
			
			//rs =pstmt.executeQuery(); 이건 select에서 사용됩니다.
			
			//3.sql 문 준비, 바인딩, 실행
			String sql = "insert into EMAILLIST values(SEQ_EMAILLIST_NO.NEXTVAL,?,?,?)";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
			
			count = pstmt.executeUpdate(); //처리하고 받아온 결과값 row (무조건 한줄이니 1입니다.)
			
			//4.결과처리
			
			System.out.println(count+"등록완료");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error page"+e);

		} catch (SQLException e) {
			System.out.println("error"+e);
		}
		

		finally {
			
			try {
				
				if(pstmt!=null) {
					pstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				//에러에 대해 깊게 안들어가고 그냥 e의 toString으로 출력하고 싶을때 !
				System.out.println("error"+e);
			}
			
		}
		
	}
	
	public ArrayList<EmailVO> getList(){
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		EmailVO vo = null;
		ArrayList<EmailVO> list= new ArrayList<EmailVO>();
		int count;
		try {
			//1.드라이버로딩
			Class.forName(driver);
			
			//2.connection얻어오기 
			con = DriverManager.getConnection(url,"webdb","webdb");
			
			
			//3.sql 문 준비, 바인딩, 실행
			String sql = " SELECT no,last_name,frist_name,email "
			            +" from emaillist "
					    +" order by no desc ";
			pstmt= con.prepareStatement(sql);
			rs =pstmt.executeQuery();
			
			//4.결과처리
			while(rs.next()) {
				vo = new EmailVO();
				vo.setNo(rs.getInt("no"));
				vo.setLastName(rs.getString("last_name"));
				vo.setFirstName(rs.getString("frist_name"));
				vo.setEmail(rs.getString("email"));
				
				list.add(vo);
				
			}			
			
				
		} catch (ClassNotFoundException e) {
			System.out.println("error page"+e);

		} catch (SQLException e) {
			System.out.println("error"+e);
		}
		

		finally {
			
			try {
				
				if(rs!=null) {
				rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				//에러에 대해 깊게 안들어가고 그냥 e의 toString으로 출력하고 싶을때 !
				System.out.println("error"+e);
			}
			
		}
			
		return list;
	}

}
