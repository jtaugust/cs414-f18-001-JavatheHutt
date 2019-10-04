package Database;

import java.io.*;
import java.sql.*;

public class DB {
	
	//JDBC driver and database
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_URL = "jdbc:mariadb://faure:3306/javathehutt";
	
	//login credentials
	private static final String USER = "jwelch31";
	private static final String PASSWORD = "cyberpunkisawesome";
	
	
	public static int isUser(String user, String pass){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		if (user.contentEquals("Username") || pass.contentEquals("Password")){
			return 1; // username or password is blank
		}
		
		try{
			BufferedReader in = new BufferedReader(new FileReader("./temporary/registeredUsers"));
			String savedUser;
			String savedPass;
			while ((savedUser = in.readLine()) != null){
				if (savedUser.equals(user)){
					savedPass = in.readLine(); //read pass
					if (savedPass.equals(pass)){
						return 0;
					}else{
						return 2; //password is wrong
					}
				}else{
					in.readLine();//skip pass
					in.readLine();//skip email
				}
			}
			return 3; //user doesnt exist
		}catch (Exception e){/* do nothing */}
		return 4; //couldnt read file
	}
	
	//Check if username or email is already in file
	public static int isTaken(String user, String email){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		/* 
		 * This is just a temporary fix until
		 * the connection issue can be diagnosed
		 */
		try{
			BufferedReader in = new BufferedReader(new FileReader("./temporary/registeredUsers"));
			String savedUser;
			String savedEmail;

			while ((savedUser = in.readLine()) != null){
				if (savedUser.equals(user)){ //user is taken
					return 3;
				}
				in.readLine(); savedEmail = in.readLine();
				if (savedEmail.equals(email)){ //email is taken
					return 4;
				}
				
			}
		
		}catch (Exception e){
			
		}
		
		return 0;
	}
	
	//Create new user in file
	public static void createUser(String user, String password, String email){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		/* 
		 * This is just a temporary fix until
		 * the connection issue can be diagnosed
		 */
		try{
			Writer output = new BufferedWriter(new FileWriter("./temporary/registeredUsers", true));
			String newUser = System.lineSeparator() + user + System.lineSeparator() + password + System.lineSeparator() + email;
			output.append(newUser);
			output.close();
			
		}catch (Exception e){
			
			
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
