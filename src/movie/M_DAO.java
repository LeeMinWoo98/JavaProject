package movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.U_DTO;

public class M_DAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	// DBMS 주소 : 포트번호 : 데이터베이스 이름

	Connection conn = null;
	
	
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
	
	public ArrayList<M_DTO> movieInfo() {
		ArrayList<M_DTO> mdto = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = connection();
		try {
			if(conn!=null) {
				String sql=	"select from";
						
						
					//"select "
					//+"m.m_type AS movie_type, "
					//+ "m.m_runningtime AS running_time, "
					//+ "t.t_location AS theater_location, "
					//+ "s.s_time AS showing_time, "
					//+ "sr.sr_num AS screenroom_number, "
					//+ "sr.sr_seat AS screenroom_seat, "
					//+ "m.m_title AS movie_title "
					//+ "FROM movie m "
					//+ "JOIN showing s ON m.m_num = s.s_mnum "
					//+ "JOIN theaters t ON s.s_tcode = t.t_code "
					//+ "JOIN screenroom sr ON t.t_code = sr.sr_code "
					//+ "ORDER BY s.s_time";
				
			
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				
				
				if (rs != null) {
					while (rs.next()) {
						M_DTO m = new M_DTO();
						m.setMovieName(rs.getString("movie_title"));
						m.setMovieType(rs.getString("movie_type"));
						m.setRunningTime(rs.getInt("running_time"));
						m.setLocation(rs.getString("theater_location"));
						m.setMovieLine(rs.getString("showing_time"));
						m.setScreenRoomNum(rs.getInt("screenroom_number"));
						m.setSeatCnt(rs.getInt("screenroom_seat"));
						mdto.add(m);
						
					}

				}
								
			}
			
		} catch (SQLException  e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mdto;
	}
}
