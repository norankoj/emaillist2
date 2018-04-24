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
			//1.����̹��ε�
			Class.forName(driver);
			
			//2.connection������ 
			con = DriverManager.getConnection(url,"webdb","webdb");
			
			//rs =pstmt.executeQuery(); �̰� select���� ���˴ϴ�.
			
			//3.sql �� �غ�, ���ε�, ����
			String sql = "insert into EMAILLIST values(SEQ_EMAILLIST_NO.NEXTVAL,?,?,?)";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
			
			count = pstmt.executeUpdate(); //ó���ϰ� �޾ƿ� ����� row (������ �����̴� 1�Դϴ�.)
			
			//4.���ó��
			
			System.out.println(count+"��ϿϷ�");
			
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
				//������ ���� ��� �ȵ��� �׳� e�� toString���� ����ϰ� ������ !
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
			//1.����̹��ε�
			Class.forName(driver);
			
			//2.connection������ 
			con = DriverManager.getConnection(url,"webdb","webdb");
			
			
			//3.sql �� �غ�, ���ε�, ����
			String sql = " SELECT no,last_name,frist_name,email "
			            +" from emaillist "
					    +" order by no desc ";
			pstmt= con.prepareStatement(sql);
			rs =pstmt.executeQuery();
			
			//4.���ó��
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
				//������ ���� ��� �ȵ��� �׳� e�� toString���� ����ϰ� ������ !
				System.out.println("error"+e);
			}
			
		}
			
		return list;
	}

}
