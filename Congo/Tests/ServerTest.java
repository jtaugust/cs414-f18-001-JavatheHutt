package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import Server.serverHelpers;

@FixMethodOrder
public class ServerTest {
	String Username = "Unregister";
	String email = "Unregister@test.com";
	String password = "Unregister";

	@Test
	public void testtryRegister() {
		int err = serverHelpers.tryRegister(Username, email, password);

		if (err == 0) {
			try {
				
				assertEquals(password, serverHelpers.readUserLogin_T(Username));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			fail();
		}

		// username is in use
		assertEquals(3, serverHelpers.tryRegister(Username, email, password));

		Username = "testingRegister";

		// email is in use
		assertEquals(4, serverHelpers.tryRegister(Username, email, password));

		password = ";";

		// password contains illegal character
		assertEquals(5, serverHelpers.tryRegister(Username, email, password));

		password = "Unregister";
		Username = "testingRegisterAgain;";

		assertEquals(6, serverHelpers.tryRegister(Username, email, password));

		Username = "testingRegisterAgain";
		email = "test";

		assertEquals(7, serverHelpers.tryRegister(Username, email, password));
		
		
		//Try Unregistering User as Well
		serverHelpers.tryUnregister("Unregister");

		try {
			assertEquals(null, serverHelpers.readUserLogin_T("Unregister"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Test
//	public void testtryUnregister() {
//			serverHelpers.tryUnregister(Username);
//			// Check if User got deleted from Database
//			try {
//				assertEquals(null, serverHelpers.readUserLogin_T(Username));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}		
//	}

	@Test
	public void testTryLoginWithGoodCredentials() {
		String username = "admin";
		String password = "test";
		assertEquals(0, serverHelpers.tryLogin(username, password));
	}

	@Test
	public void testTryLoginWithBadUser() {
		String username = "admin3";
		String password = "test";
		assertEquals(2, serverHelpers.tryLogin(username, password));
	}

	@Test
	public void testTryLoginWithGoodUserAndBadPassword() {
		String username = "admin";
		String password = "Test";
		assertEquals(2, serverHelpers.tryLogin(username, password));
	}

}
