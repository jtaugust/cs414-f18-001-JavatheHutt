package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Server.serverHelpers;
import junit.framework.Assert;

public class ServerTest {

	@Test
	public void testtryUnregister() {

		String Username = "Unregister";
		String email = "Unregister@test.com";
		String password = "Unregister";

		int err = serverHelpers.tryRegister(Username, email, password);

		if (err == 0) {

			try {
				// Test that the new user was added to the database
				if (password.equals(serverHelpers.readUserLogin_T(Username))) {

					serverHelpers.tryUnregister(Username);
					// Check if User got deleted from Database
					assertEquals(null, serverHelpers.readUserLogin_T(Username));
					
				} else {
					fail();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			fail();
		}

	}

}
