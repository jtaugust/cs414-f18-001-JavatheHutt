package Tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.Before;
import org.junit.runners.JUnit4;
import org.junit.runner.RunWith;
import Database.*;
//import Screens.RegistrationScreen;
//import Screens.LoginScreen;

@RunWith(JUnit4.class)
public class TestDB {

	String user;
	String email;
	String password;

	@Before
	public void initialize() {

		user = "testUser";
		email = "test@gmail.com";
		password = "password";

	}

	@Test
	public void testCreateUser() throws IOException {
		//add test user to file
		DB.createUser(user, password, email);
		
		//check that user was added
		boolean added = false;
		BufferedReader in = new BufferedReader(new FileReader("./temporary/registeredUsers"));
		BufferedWriter out = new BufferedWriter(new FileWriter("./temporary/registeredUsers",true));
		String savedUser;
		String savedPass;
		while ((savedUser = in.readLine()) != null) {
			if (savedUser.equals(user)) {
				savedPass = in.readLine(); // read pass
				if (savedPass.equals(password)) {
					added = true;
				} else {
					added = false;
				}
			} else {
				in.readLine();// skip pass
				in.readLine();// skip email
			}
		}
		assertTrue(added);
		
	}

	@Test
	public void testIsUser() {

		assertTrue(DB.isUser(user, password));

	}

	@Test
	public void testIsTaken() {

		assertEquals(3, DB.isTaken(user, password));

	}

}