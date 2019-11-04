package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Server.serverHelpers;
import junit.framework.Assert;

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
				}catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				fail();
			}
		}
		
	@Test
	public void testtryUnregister() {
			serverHelpers.tryUnregister(Username);
			// Check if User got deleted from Database
			try {
				assertEquals(null, serverHelpers.readUserLogin_T(Username));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
