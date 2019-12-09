package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Blob;

public class serverHelpers {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	// Returns String[] that shows table Column Names for Users DataBase
	// The list of tables are AccountStats_T, MatchHistory_T, UserCaptcha_T,
	// UserInfo_T, UserInvites_T, UserLogin_T
	public String[] getUsersTableInfo(String tableName) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			resultSet = statement.executeQuery("SELECT * FROM Users." + tableName);

			String[] returnArray = new String[resultSet.getMetaData().getColumnCount()];

			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {

				returnArray[i] = resultSet.getMetaData().getColumnName(i + 1);

			}

			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Creates new row in AccountStats_T
	public void createAccountStats_T(String[] values) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			preparedStatement = connect
					.prepareStatement("INSERT INTO Users.AccountStats_T VALUES (?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, values[0]);

			for (int i = 2; i <= 7; i++) {

				String item = values[i-1];
				int j = Integer.parseInt(item);
				preparedStatement.setInt(i, j);

			}

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// returns a String[] of data values for a given user from AccountStats_T
	public String[] readAccountStats_T(String accountName) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT * FROM Users.AccountStats_T WHERE Username=(?)");

			preparedStatement.setString(1, accountName);
			resultSet = preparedStatement.executeQuery();

			String[] returnArray = new String[resultSet.getMetaData().getColumnCount()];

			returnArray[0] = accountName;
			resultSet.next();
			for (int i = 2; i <= 7; i++) {
				String col = resultSet.getMetaData().getColumnName(i);
				String item = Integer.toString(resultSet.getInt(col));
				returnArray[i - 1] = item;
			}

			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Change a certain value for a User in AccountStats_T given a specific column
	// name and value
	public void insertAccountStats_T(String Username, String column, String value) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			if (column != "Username") {

				preparedStatement = connect.prepareStatement("UPDATE Users.AccountStats_T SET " + column + " = "
						+ Integer.parseInt(value) + " WHERE Username = (?)");

				preparedStatement.setString(1, Username);

				preparedStatement.executeUpdate();
			}

			else {
				System.out.println("Cannot change username here.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Creates new row in UserInfo_T
	public static void createUserInfo_T(String username, String email, String description, byte[] profilePic)
			throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			preparedStatement = connect.prepareStatement("INSERT INTO Users.UserInfo_T VALUES (?, ?, ?, ?, default)");

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, description);
			preparedStatement.setBytes(4, profilePic);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// returns a String[] of data values for a given user from UserInfo_T
	public String[] readUserInfo_T(String accountName) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT * FROM Users.UserInfo_T WHERE Username=(?)");

			preparedStatement.setString(1, accountName);
			resultSet = preparedStatement.executeQuery();

			String[] returnArray = new String[resultSet.getMetaData().getColumnCount()];

			returnArray[0] = accountName;

			resultSet.next();
			for (int i = 2; i <= 3; i++) {
				String col = resultSet.getMetaData().getColumnName(i);
				String item = resultSet.getString(col);
				returnArray[i - 1] = item;
			}

			Blob profilePic = resultSet.getBlob("ProfileImage");

			if (profilePic != null) {
				returnArray[3] = profilePic.toString();
			}

			Timestamp time = resultSet.getTimestamp("LastLoggedIn");

			returnArray[4] = time.toString();

			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Change a certain value for a User in UserInfo_T given a specific column name
	// and value
	// When updating LastLoggedIn it will get currentTime leave value set to null
	public void insertUserInfo_T(String Username, String column, String value) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			if (column != "Username") {

				if (column != "ProfileImage" && column != "LastLoggedIn") {

					preparedStatement = connect.prepareStatement(
							"UPDATE Users.UserInfo_T SET " + column + " = '" + value + "' WHERE Username = (?)");

					preparedStatement.setString(1, Username);

					preparedStatement.executeUpdate();
				}
				// When updating LastLoggedIn it will get currentTime leave value set to null
				else if (column == "LastLoggedIn") {

					preparedStatement = connect.prepareStatement(
							"UPDATE Users.UserInfo_T SET " + column + " = CURRENT_TIMESTAMP" + " WHERE Username = (?)");

					preparedStatement.setString(1, Username);

					preparedStatement.executeUpdate();
				}

			}

			else {
				System.out.println("Cannot change username here.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Creates a new row in MatchHistory_T
	// Only include none dateTime values in String[]
	public void createMatchHistory_T(String[] values) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			preparedStatement = connect
					.prepareStatement("INSERT INTO Users.MatchHistory_T VALUES (?, ?, ?, default, default, ?)");

			preparedStatement.setInt(1, Integer.parseInt(values[0]));
			preparedStatement.setString(2, values[1]);
			preparedStatement.setString(3, values[2]);
			preparedStatement.setString(4, values[3]);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// returns a String[] of data values for a given user and gameNumber from
	// MatchHistory_T
	public String[] readMatchHistory_T(String accountName, int gameNumber) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect
					.prepareStatement("SELECT * FROM Users.MatchHistory_T WHERE Username=(?) AND GameNumber=(?)");

			preparedStatement.setString(1, accountName);
			preparedStatement.setInt(2, gameNumber);
			resultSet = preparedStatement.executeQuery();

			String[] returnArray = new String[resultSet.getMetaData().getColumnCount()];

			returnArray[0] = Integer.toString(gameNumber);
			returnArray[1] = accountName;

			resultSet.next();

			returnArray[2] = resultSet.getString("Opponent");

			Timestamp time = resultSet.getTimestamp("Start_Time");

			returnArray[3] = time.toString();

			time = resultSet.getTimestamp("End_Time");

			if (time == null) {
				returnArray[4] = null;
			} else {
				returnArray[4] = time.toString();
			}

			returnArray[5] = resultSet.getString("Status");

			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	
	// returns a String[][] of data values for a given user from MatchHistory_T
	public String[][] readMatchHistory_T(String accountName) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect
					.prepareStatement("SELECT * FROM Users.MatchHistory_T WHERE Username=(?) OR Opponent=(?)");

			preparedStatement.setString(1, accountName);
			preparedStatement.setString(2, accountName);
			resultSet = preparedStatement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			
			int gameCount = 0;
			while(resultSet.next()) {
				gameCount++;
			}
			
			if(gameCount == 0) {
				return null;
			}
			
			resultSet.beforeFirst();
			int columnCount = rsmd.getColumnCount();
			String[][] returnArray = new String[gameCount][columnCount];
			
			int gameIndex = 0;
			while (resultSet.next()) {
			    for (int i = 1; i <= columnCount; i++) {
			        String columnValue = resultSet.getString(i);
			        returnArray[gameIndex][i-1] = columnValue;
			    }
			    System.out.println("");
			    gameIndex++;
			}

			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Change a certain value for a User and GameNumber in MatchHistory_T given a
	// specific column name and value
	// When updating Start_Time or End_Time it will get currentTime leave value set
	// to null
	public void insertMatchHistory_T(String Username, int gameNumber, String column, String value) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			if (column != "Username") {

				if (column != "Start_Time" && column != "End_Time" && column != "GameNumber") {

					preparedStatement = connect.prepareStatement("UPDATE Users.MatchHistory_T SET " + column + " = '"
							+ value + "' WHERE Username=(?) AND GameNumber=(?)");

					preparedStatement.setString(1, Username);
					preparedStatement.setInt(2, gameNumber);

					preparedStatement.executeUpdate();
				}
				// When updating LastLoggedIn it will get currentTime leave value set to null
				else if (column == "Start_Time" || column == "End_Time") {

					preparedStatement = connect.prepareStatement("UPDATE Users.MatchHistory_T SET " + column
							+ " = CURRENT_TIMESTAMP" + " WHERE Username = (?) AND GameNumber = (?)");

					preparedStatement.setString(1, Username);
					preparedStatement.setInt(2, gameNumber);

					preparedStatement.executeUpdate();
				} else if (column == "GameNumber") {

					preparedStatement = connect.prepareStatement("UPDATE Users.MatchHistory_T SET " + column + " = "
							+ value + " WHERE Username = (?) AND GameNumber = (?)");

					preparedStatement.setString(1, Username);
					preparedStatement.setInt(2, gameNumber);

					preparedStatement.executeUpdate();
				}

			}

			else {
				System.out.println("Cannot change username here.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Creates a new row in UserCaptcha_T
	public void createUserCaptcha_T(String accountName, String captcha) throws Exception {
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			preparedStatement = connect.prepareStatement("INSERT INTO Users.UserCaptcha_T VALUES (?, ?)");

			preparedStatement.setString(1, captcha);
			preparedStatement.setString(2, accountName);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// returns a String[] of data values for a given user from UserCaptcha_T
	public String[] readUserCaptcha_T(String accountName) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT * FROM Users.UserCaptcha_T WHERE Username=(?)");

			preparedStatement.setString(1, accountName);
			resultSet = preparedStatement.executeQuery();

			String[] returnArray = new String[resultSet.getMetaData().getColumnCount()];

			returnArray[1] = accountName;

			resultSet.next();

			returnArray[0] = resultSet.getString(1);

			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Change a certain value for a User in UserCaptcha_T given a specific column
	// name and value
	public void insertUserCaptcha_T(String Username, String column, String value) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			if (column != "Username") {

				preparedStatement = connect.prepareStatement(
						"UPDATE Users.UserCaptcha_T SET " + column + " = " + value + " WHERE Username = (?)");

				preparedStatement.setString(1, Username);

				preparedStatement.executeUpdate();
			}

			else {
				System.out.println("Cannot change username here.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Creates a new row in UserInvites_T
	public void createUserInvites_T(String sender, String receiver, String status) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			preparedStatement = connect.prepareStatement("INSERT INTO Users.UserInvites_T VALUES (default, ?, ?, ?)");

			preparedStatement.setString(1, sender);
			preparedStatement.setString(2, receiver);
			preparedStatement.setString(3, status);
			
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// inviteNumber is the primaryKey as such this is the best way of getting the
	// exact Invite wanted. Returns String[] from UserInvites_T
	public String[] readUserInvites_T(int inviteNumber) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT * FROM Users.UserInvites_T WHERE InviteNumber=(?)");

			preparedStatement.setInt(1, inviteNumber);
			resultSet = preparedStatement.executeQuery();

			String[] returnArray = new String[resultSet.getMetaData().getColumnCount()];

			returnArray[0] = Integer.toString(inviteNumber);

			resultSet.next();

			returnArray[1] = resultSet.getString("Sender");
			returnArray[2] = Integer.toString(resultSet.getInt("Status"));
			returnArray[3] = resultSet.getString("Receiver");

			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	
	public ArrayList<String> getAllUsers(String thisUser) throws Exception{
		try{
			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Games?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT Username FROM Users.UserInfo_T WHERE Username!=(?)");

			preparedStatement.setString(1, thisUser);
			resultSet = preparedStatement.executeQuery();
			
			ArrayList<String> returnArray = new ArrayList<String>();

			while(resultSet.next()){
				String user = resultSet.getString(1);
				returnArray.add(user);
			}
			
			return returnArray;
		}catch (Exception e){
			throw e;
		}finally{
			close();
		}
	}
	
	//Overload to get all invites a given user has received.
	public ArrayList<String[]> readReceivedUserInvites_T(String Username) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Games?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT * FROM Users.UserInvites_T WHERE Receiver=(?)");

			preparedStatement.setString(1, Username);
			resultSet = preparedStatement.executeQuery();
			
			ArrayList<String[]> returnArray = new ArrayList<String[]>();
			
			while(resultSet.next()) {
				String[] rowArray = new String[resultSet.getMetaData().getColumnCount()];
				rowArray[0] = Integer.toString(resultSet.getInt(1));
				rowArray[1] = resultSet.getString(2);
				rowArray[2] = resultSet.getString(3);
				rowArray[3] = resultSet.getString(4);
				returnArray.add(rowArray);
			}
			
			connect.close();
			
			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	
	//Overload to get all invites a given user has received.
	public ArrayList<String[]> readSentUserInvites_T(String Username) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Games?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT * FROM Users.UserInvites_T WHERE Sender=(?)");

			preparedStatement.setString(1, Username);
			resultSet = preparedStatement.executeQuery();
			
			ArrayList<String[]> returnArray = new ArrayList<String[]>();
			
			while(resultSet.next()) {
				String[] rowArray = new String[resultSet.getMetaData().getColumnCount()];
				rowArray[0] = Integer.toString(resultSet.getInt(1));
				rowArray[1] = resultSet.getString(2);
				rowArray[2] = resultSet.getString(3);
				rowArray[3] = resultSet.getString(4);
				returnArray.add(rowArray);
			}
			
			connect.close();
			
			return returnArray;

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Change a certain value for a User in UserInvites_T given a specific column
	// inviteNumber and value
	public void insertUserInvites_T(int inviteNumber, String column, String value) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			if (column != "InviteNumber" && column != "Status") {

				preparedStatement = connect.prepareStatement(
						"UPDATE Users.UserInvites_T SET " + column + " = '" + value + "' WHERE InviteNumber = (?)");

				preparedStatement.setInt(1, inviteNumber);

				preparedStatement.executeUpdate();
			}

			else if (column == "InviteNumber" || column == "Status") {

				preparedStatement = connect.prepareStatement(
						"UPDATE Users.UserInvites_T SET " + column + " = '" + value + "' WHERE InviteNumber = (?)");

				preparedStatement.setInt(1, inviteNumber);

				preparedStatement.executeUpdate();

			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Adds a new row to UserLogin_T
	public static void createUserLogin_T(String Username, String Password) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			preparedStatement = connect.prepareStatement("INSERT INTO Users.UserLogin_T VALUES (?, ?)");

			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// returns a String[] of data values for a give user from UserLogin_T
	public static String readUserLogin_T(String Username) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			statement = connect.createStatement();

			preparedStatement = connect.prepareStatement("SELECT * FROM Users.UserLogin_T WHERE Username=(?)");

			preparedStatement.setString(1, Username);
			resultSet = preparedStatement.executeQuery();
			
			//this needs to occur everywhere
			//if resultSet is null, return
			if (!resultSet.first()) {
				return null;
			}

			return resultSet.getString("Password");

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	// Really just a way to update a change in password for a given user.
	public int insertUserLogin_T(String Username, String column, String value) throws Exception {
		if (!validString(value)){
			return 4; //password contains illegal characters
		}
		try {

			Class.forName("org.mariadb.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			if (column != "Username") {

				preparedStatement = connect.prepareStatement(
						"UPDATE Users.UserLogin_T SET " + column + " = '" + value + "' WHERE Username = (?)");

				preparedStatement.setString(1, Username);

				preparedStatement.executeUpdate();
			} else {
				System.out.println("Cannot change username here.");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return 0;
	}
	
	public static int tryLogin(String username, String password) {
		String loginPass;
		if (!validString(password)){
			return 4; //password contains illegal characters
		}else if (!validString(username)){
			return 5; //Username contains illegal characters
		}
		try {
			loginPass = readUserLogin_T(username);
			if (loginPass == null) { // no such user
				return 2; //bad user or password
			}else {
				if (loginPass.equals(password)) {
					return 0; // good user
				}else {
					return 2; //bad user or pass
				}
			}
		} catch (Exception e) {
			return 3;
			//readUserLogin can throw either a bad connection to pi or a bad sql query
		}
	}

	// When creating a new user call this with their new account info like
	public static int tryRegister(String Username, String email, String password){
		if (!validString(password)){
			return 5; //password contains illegal characters
		}else if (!validString(Username)){
			return 6;
		}else if (!validEmail(email)){
			return 7;
		}
		try{
			createUserInfo_T(Username, email, null, null);
			createUserLogin_T(Username, password);
		}catch (Exception e){
			//parse error, is it on username or email
			String sqlerr = e.getLocalizedMessage();
			String sql[] = sqlerr.split("\'");
			sqlerr = sql[1];
			if (sqlerr.equals(email)){
				return 4;
			}else if (sqlerr.equals(Username)){
				return 3;
			}else{
				return 6; //unknown error
			}
		}
		//createUserLogin_T(Username, password);
		return 0;
	}

	public static int tryUnregister(String Username) {
		
		try {
			deleteUser(Username);
		}catch (Exception e) {
			
			String sqlerr = e.getLocalizedMessage();
			String sql[] = sqlerr.split("\'");
			sqlerr = sql[1];
			
			if(sqlerr.equals(Username)) {
				return 1;
			}else {
				return 2;
			}
		}
		return 0;	
	}
	
	public static void deleteUser(String Username) throws Exception {

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://68.234.149.213:8555/Users?" + "user=cs414&password=cs414");

			//Clear from UserInfo_T
			preparedStatement = connect.prepareStatement("DELETE FROM Users.UserInfo_T WHERE Username = (?)");
			preparedStatement.setString(1, Username);
			preparedStatement.executeUpdate();


		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	private static boolean validString(String str){
		if (str.matches("^[A-Za-z0-9]+$")){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean validEmail(String email){
		if (email.matches("^[A-Za-z0-9\\Q._-+\\E]+@[A-Za-z0-9\\Q.-\\E]+$")){
			return true;
		}else{
			return false;
		}
	}

	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
