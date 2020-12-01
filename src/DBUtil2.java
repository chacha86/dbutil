import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUtil2 {
	// 드라이버 정보
	String driver = "com.mysql.cj.jdbc.Driver";
	// dbms 주소p
	String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";

	// 사용자 계정
	String user = "root";
	// 사용자 비밀번호
	String pass = "";

	Connection conn = null;

	public PreparedStatement getPrepareStatement(String sql, Object[] params) throws SQLException {
		PreparedStatement pstmt = null;
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);

		for (int i = 0; i < params.length; i++) {
			if (params[i] instanceof Integer) {
				pstmt.setInt(i + 1, (int) params[i]);
			} else {
				pstmt.setString(i + 1, (String) params[i]);
			}
		}

		return pstmt;
	}

	public <T> T getRow(String sql, MapperInterface<T> mapper, Object...params) {
		return getRows(sql, mapper, params).get(0);
	}
	
	public <T> ArrayList<T> getRows(String sql, MapperInterface<T> mapper, Object...params) {
		if(params.length != 0 && params[0] instanceof Object[]) {
			params = (Object[])params[0];
		}
		Class<T> c = (Class<T>) mapper.getClass();
		ArrayList<T> rows = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = getPrepareStatement(sql, params);
			rs = pstmt.executeQuery();

			Field[] fields = c.getDeclaredFields();
			T obj = null;
			while (rs.next()) {
				try {
					obj = c.newInstance();
				} catch (InstantiationException | IllegalAccessException e1) {
					e1.printStackTrace(); 	
				}
				for(int i = 0; i < fields.length; i++) {
					
					String fieldName = fields[i].getName();
					String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Method m = null;
					try {
						m = c.getDeclaredMethod(setterName, fields[i].getType());
						
						if(fields[i].getType().getTypeName().equals("int")) {
							m.invoke(obj,rs.getInt(fieldName));
						} else {
							m.invoke(obj,rs.getString(fieldName));
						}		
					} catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				rows.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return rows;
	}

//	public Article getRow(String sql, Object... params) {
//		return getRows(sql, params).get(0);
//	}
//
//	public int updateQuery(String sql, Object... params) {
//		if (params.length != 0 && params[0] instanceof Object[]) {
//			params = (Object[]) params[0];
//		}
//
//		int rst = 0;
//		PreparedStatement pstmt = null;
//
//		try {
//			System.out.println(sql);
//			pstmt = getPrepareStatement(sql, params);
//			rst = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt, conn);
//		}
//		System.out.println(rst);
//		return rst;
//	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			close(pstmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(PreparedStatement pstmt, Connection conn) {

		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
