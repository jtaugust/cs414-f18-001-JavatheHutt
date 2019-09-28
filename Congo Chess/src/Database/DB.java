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
		
		try {
			//register driver
			Class.forName(JDBC_DRIVER);
		
			//open connection
			conn = generateConnection();
			
			//create the query
			String sql = "select Username, Password from LoginInformation"
					+ "where (Username ==" + USER;
			
			//Execute the query and store the result
			ResultSet rs = generateStatement(conn, sql);
			if (rs.getString("Username").contentEquals(user) && rs.getString("Password").contentEquals(pass)) {
				System.out.println("here");
				return true;
			}else {
				return false;
			}
			
		} catch(SQLException se) {
			System.out.println(se);
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public static Connection generateConnection() throws Exception{
		return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}
	
	public static ResultSet generateStatement(Connection conn, String sql)throws Exception{
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(sql);
	}
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