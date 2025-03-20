package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public void deleteGuest(String id) {
		PreparedStatement stmt = null;
		try {
			conn = connection();
			if(conn!=null) {
				String sql = "delete from admin where id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.executeUpdate();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생");
				e.printStackTrace();
			}
		}
	}
	
	public int checkGuest(String id, String guest) {
		int a = 0;
		ArrayList<U_DTO> ulist = new ArrayList<>();
		ulist = userList();
		System.out.println("게스트 체크!");
		for(int i=0; i<ulist.size(); i++) {
			if(ulist.get(i).getGuest().equals(guest)) {
				if(ulist.get(i).getId().equals(id)) {
					a=1;
				}
			}else
				a=2;
		}
		return a;
	}

	public int guest(String phone, String name) {
		PreparedStatement stmt = null;
		int a=0;
		try {
			conn = connection();
			if(conn!=null) {
				String sql = "insert into admin values(?, ?, null, ?, null, 1)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, phone);
				stmt.setString(2, phone);
				stmt.setString(3, name);
				stmt.executeUpdate();
				a=1;
			}
		} catch (Exception e) {
			
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생");
				e.printStackTrace();
			}
		}
		return a;
	}

	public int idcheck(String id) {
		int idcheck = 0;
		ArrayList<U_DTO> ulist = new ArrayList<>();

		ulist = userList();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getId().equals(id)) {
				idcheck = 1;
			} else
				idcheck = 0;
		}
		return idcheck;
	}

	public int insert(U_DTO u) {
		PreparedStatement stmt = null;
		int joincheck = 0;
		try {
			conn = connection();
			if (conn != null) {
				String sql = "insert into admin values(?, ?, ?, ?, ?, 0)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, u.getId());
				stmt.setString(2, u.getPhone());
				stmt.setString(3, u.getPw());
				stmt.setString(4, u.getName());
				stmt.setString(5, u.getB_day());
				stmt.executeUpdate();
				joincheck = 1;
			}
		} catch (Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
			joincheck = 2;
		} finally {
			try {
				// 연결 해제, 자원 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return joincheck;
	}

	public int login(String id, String pw) {
		int a = 0;
		ArrayList<U_DTO> ulist = new ArrayList<>();
		ulist = userList();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getId().equals(id)) {
				if (ulist.get(i).getPw().equals(pw)) {
					// 로그인 성공
					// a = 0;
					a = 0;
				} else {
					// 비밀번호 틀림
					// a = 1;
					a = 1;
				}
			} else {
				// 아이디 없음
				// a = 2;
				a = 2;
			}
		}
		return a;

	}

	public int resign(String id, String pw) {
		ArrayList<U_DTO> ulist = new ArrayList<>();
		ulist = userList();
		int a = 100;
		PreparedStatement stmt = null;
		if (ulist.isEmpty() != true) {
			for (int i = 0; i < ulist.size(); i++) {
				if (ulist.get(i).getPw().equals(pw) && ulist.get(i).getPw().equals(pw)) {
					try {
						conn = connection();
						if (conn != null) {
							System.out.println("99");
							String sql = "delete from admin where a_id =?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, id);
							stmt.executeUpdate();
							a = 0;
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
				} else if (ulist.get(i).getId().equals(id)) {
					a = 1;
				} else
					a = 2;
			}

		} else {

			a = 2;
		}
		return a;
	}

	public ArrayList<U_DTO> userList() {
		ArrayList<U_DTO> ulist = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = connection();
			if (conn != null) {
				sql = "select* from admin";
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
				rs = stmt.executeQuery(sql);

				if (rs != null) {
					while (rs.next()) {
						U_DTO udto = new U_DTO();
						udto.setId(rs.getString("a_id"));
						udto.setPhone(rs.getString("a_phone"));
						udto.setPw(rs.getString("a_pw"));
						udto.setName(rs.getString("a_name"));
						udto.setB_day(rs.getString("a_bday"));
						udto.setGuest(rs.getString("a_guest"));
						ulist.add(udto);
					}

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
		return ulist;
	}
}
