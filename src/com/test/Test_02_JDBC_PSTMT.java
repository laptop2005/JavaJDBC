package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test_02_JDBC_PSTMT {
	public static void main(String[] args) {
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String DB_USER = "system";
		String DB_PASSWORD = "system";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT * FROM TEST WHERE TEST_ID = ?";
		try {
			// ����̹��� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			// �����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// PreparedStatement�� �����´�.
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "0000000001");

			// SQL���� �����Ѵ�.
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String test_id = rs.getString(1);
				String test_name = rs.getString(2);
				String test_date = rs.getString(3);
				// ����� ����Ѵ�.
				System.out.println(test_id + " : " + test_name + " : " + test_date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// ResultSet�� �ݴ´�.
				if(rs!=null){
					rs.close();
				}
				// PreparedStatement�� �ݴ´�.
				if(pstmt!=null){
					pstmt.close();
				}
				// Connection�� �ݴ´�.
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	} // main()�� ��
}
