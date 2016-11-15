package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test_01_JDBC_STMT {
	public static void main(String[] args) {
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String DB_USER = "system";
		String DB_PASSWORD = "system";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String query = "SELECT * FROM TEST WHERE TEST_ID = ";
		
		query += "\'0000000001\'";
		
		query += " OR 1=1";
		
		try {
			// ����̹��� �ε��Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			// �����ͺ��̽��� ������ �����Ѵ�.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// Statement�� �����´�.
			stmt = conn.createStatement();

			// SQL���� �����Ѵ�.
			rs = stmt.executeQuery(query);

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
				// Statement�� �ݴ´�.
				if(stmt!=null){
					stmt.close();
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
