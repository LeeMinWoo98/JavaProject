package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class U_DAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	// DBMS 주소 : 포트번호 : 데이터베이스 이름
	Connection conn = null;

	U_DTO Udto = new U_DTO();

	public Connection connection() {
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			System.out.println("연결 성공");
		} catch (Exception e) {
			System.out.println("연결 실패");
			conn = null;
		}
		return conn;
	}

	public void insert(U_DTO u) {
		PreparedStatement stmt = null;
		try {
			conn = connection();
			if (conn != null) {
				System.out.println("넘어왔다");
				String sql = "insert into admin values(?, ?, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, u.getId());
				stmt.setString(2, u.getPw());
				stmt.setString(3, u.getName());
				stmt.setString(4, u.getB_day());
				stmt.executeUpdate();

			}
		} catch (Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		} finally {
			try {
				// 연결 해제, 자원 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int login(String id, String pw) {
		int a=0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = connection();
			if (conn != null) {
				sql = "select* from admin where a_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.executeUpdate();
				rs = stmt.executeQuery(sql);

				if (rs != null) {
					while (rs.next()) {
						U_DTO udto = new U_DTO();
						udto.setId(rs.getString("a_id"));
						udto.setPw(rs.getString("a_id"));
						udto.setName(rs.getString("a_id"));
						udto.setB_day(rs.getString("a_id"));
						Udto = udto;
					}
					if (Udto.getPw().equals(pw)) {
						// 로그인 성공
						a = 0;
					} else {
						// 비밀번호 틀림
						a = 1;
					}
				} else {
					// 아이디 없음
					a = 2;
				}

			}
		} catch (Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		} finally {
			try {
				// 연결 해제, 자원 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;

	}

	public void resign() {

	}
}
