package Database;

public class DB {
	public static boolean isUser(String user, String pass){
		if (user.equals("test")){ //user exists in database
			if (pass.equals("pass")){
				return true;
			}
			return false; //password wasnt correct
		}
		return false; //username wasnt correct
	}
}