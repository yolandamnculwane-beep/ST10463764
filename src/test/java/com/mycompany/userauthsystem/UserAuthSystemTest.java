package com.mycompany.userauthsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAuthSystemTest {

    @Test
    public void testCheckUserName() {
        assertTrue(UserAuthSystem.checkUserName("user_"));   // valid
        assertFalse(UserAuthSystem.checkUserName("user"));   // missing _
        assertFalse(UserAuthSystem.checkUserName("longusername")); // too long
    }

    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(UserAuthSystem.checkPasswordComplexity("Passw0rd!")); // valid
        assertFalse(UserAuthSystem.checkPasswordComplexity("password")); // no capital, number, special
        assertFalse(UserAuthSystem.checkPasswordComplexity("Password")); // no number, special
        assertFalse(UserAuthSystem.checkPasswordComplexity("Pass1234")); // no special char
    }

    @Test
    public void testCheckCellPhoneNumber() {
        assertTrue(UserAuthSystem.checkCellPhoneNumber("+27123456789")); // valid
        assertFalse(UserAuthSystem.checkCellPhoneNumber("0123456789"));  // missing +27
        assertFalse(UserAuthSystem.checkCellPhoneNumber("+2712345678")); // too short
    }

    @Test
    public void testRegisterUser() {
        assertEquals("Username and password successfully captured. User registered!",
                UserAuthSystem.registerUser("user_", "Passw0rd!"));

        assertEquals("Username is not correct. Must have _ and max 5 chars.",
                UserAuthSystem.registerUser("user", "Passw0rd!"));

        assertEquals("Password is not correct. Must have 8+ chars, capital letter, number, and special char.",
                UserAuthSystem.registerUser("user_", "password"));
    }

    @Test
    public void testLoginUser() {
        String storedUsername = "user_";
        String storedPassword = "Passw0rd!";

        assertTrue(UserAuthSystem.loginUser("user_", "Passw0rd!", storedUsername, storedPassword));
        assertFalse(UserAuthSystem.loginUser("wrong", "Passw0rd!", storedUsername, storedPassword));
        assertFalse(UserAuthSystem.loginUser("user_", "wrong", storedUsername, storedPassword));
    }

    @Test
    public void testReturnLoginStatus() {
        assertEquals("Login successful! Welcome back!", UserAuthSystem.returnLoginStatus(true));
        assertEquals("Username or password incorrect, please try again.", UserAuthSystem.returnLoginStatus(false));
    }
}