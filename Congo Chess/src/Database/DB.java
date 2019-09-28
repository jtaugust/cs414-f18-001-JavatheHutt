package Database;

import java.sql.*;

public class DB {
	
	//JDBC driver and database
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_URL = "jdbc:mariadb://faure:3306/javathehutt";
	
	//login credentials
	private static final String USER = "jwelch31";
	private static final String PASSWORD = "cyberpunkisawesome";
			
	public static boolean isUser(String user, String pass){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		/* 
		 * This is just a temporary fix until
		 * the connection issue can be diagnosed
		 */
		
		
		if (user.equals("Admin1") && pass.equals("tempPass")) {
			return true;
		}else {
			return false;
		}
		
	}
		
		
		/*
		 * 
		 * Un-comment below when fix has been found
		 * 
		 *
		
		try {
			//register driver
			Class.forName(JDBC_DRIVER);
		
			//open connection
			conn = generateConnection();
			
			//create statement
			stmt = conn.createStatement();
			
			//create the query
			String sql = "select Username, Password from LoginInformation"
					+ "where (Username ==" + USER;
			
			//Execute the query and store the result
			//rs = generateStatement(stmt, sql);
			
			if (rs.getString("Username").contentEquals(user) && rs.getString("Password").contentEquals(pass)) {
				return true;
			}else {
				return false;
			}
			
		} catch(SQLException se) {
			System.out.println(se);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException e) {}
			}
			if (stmt != null) {
				try {
					stmt.close();
				}catch (SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				}catch (SQLException e) {}
			}
		}
		return false;
	}
	
	private static Connection generateConnection() throws Exception{
		return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}
	
	private static ResultSet generateStatement(Statement stmt, String sql)throws Exception{
		return stmt.executeQuery(sql);
	}
	
	*/
	
}

/*
if (user.equals("test")){ //user exists in database
			if (pass.equals("pass")){
				return true;
			}
			return false; //password wasnt correct
		}
		return false; //username wasnt correct

*/