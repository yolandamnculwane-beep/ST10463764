
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.consolesystem;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

/**
 *
 * @author Yolanda
 */
public class ConsoleSystemTest {

    public ConsoleSystemTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Starting ConsoleSystem Tests...");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Finished ConsoleSystem Tests...");
    }

    @BeforeEach
    public void setUp() {

        // Clear all stored data before each test
        ConsoleSystem.ids.clear();
        ConsoleSystem.messages.clear();
        ConsoleSystem.recipients.clear();
        ConsoleSystem.timestamps.clear();
        ConsoleSystem.hashes.clear();
        ConsoleSystem.statusList.clear();

        ConsoleSystem.sentCount = 0;
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkUserName method, of class ConsoleSystem.
     */
    @Test
    public void testCheckUserName_Valid() {

        String u = "ab_cd";

        boolean result = ConsoleSystem.checkUserName(u);

        assertTrue(result);
    }

    @Test
    public void testCheckUserName_Invalid() {

        String u = "abcdef";

        boolean result = ConsoleSystem.checkUserName(u);

        assertFalse(result);
    }

    /**
     * Test of checkPassword method, of class ConsoleSystem.
     */
    @Test
    public void testCheckPassword_Valid() {

        String p = "Password1!";

        boolean result = ConsoleSystem.checkPassword(p);

        assertTrue(result);
    }

    @Test
    public void testCheckPassword_Invalid() {

        String p = "pass";

        boolean result = ConsoleSystem.checkPassword(p);

        assertFalse(result);
    }

    /**
     * Test of login method, of class ConsoleSystem.
     */
    @Test
    public void testLogin_Success() {

        String u = "ab_cd";
        String p = "Password1!";

        boolean result = ConsoleSystem.login(u, p, u, p);

        assertTrue(result);
    }

    @Test
    public void testLogin_Fail() {

        boolean result = ConsoleSystem.login(
                "user",
                "pass",
                "admin",
                "Password1!"
        );

        assertFalse(result);
    }

    /**
     * Test of validateNumber method, of class ConsoleSystem.
     */
    @Test
    public void testValidateNumber_Valid() {

        String num = "+27123456789";

        String result = ConsoleSystem.validateNumber(num);

        assertEquals("Valid", result);
    }

    @Test
    public void testValidateNumber_InvalidPrefix() {

        String num = "0712345678";

        String result = ConsoleSystem.validateNumber(num);

        assertEquals("Invalid: must start with +27", result);
    }

    @Test
    public void testValidateNumber_InvalidLength() {

        String num = "+27123";

        String result = ConsoleSystem.validateNumber(num);

        assertEquals("Invalid: must be 12 digits (+27XXXXXXXXX)", result);
    }

    /**
     * Test of createMessageHash method, of class ConsoleSystem.
     */
    @Test
    public void testCreateMessageHash() {

        String id = "1234567890";
        int num = 1;
        String msg = "Hello World";

        String expResult = "12:1:HELLOWORLD";

        String result = ConsoleSystem.createMessageHash(id, num, msg);

        assertEquals(expResult, result);
    }

    /**
     * Test of storeMessage method, of class ConsoleSystem.
     */
    @Test
    public void testStoreMessage() {

        // Add sample message data
        ConsoleSystem.ids.add("1234567890");
        ConsoleSystem.hashes.add("12:1:HELLOWORLD");
        ConsoleSystem.recipients.add("+27123456789");
        ConsoleSystem.messages.add("Hello World");
        ConsoleSystem.timestamps.add("10:00:00");
        ConsoleSystem.statusList.add("SENT");

        ConsoleSystem.storeMessage();

        File file = new File("messages.json");

        assertTrue(file.exists());
    }

    /**
     * Test of showMessages method, of class ConsoleSystem.
     */
    @Test
    public void testShowMessages() {

        assertDoesNotThrow(() -> {
            ConsoleSystem.showMessages();
        });
    }

    /**
     * Basic test of main method.
     * NOTE:
     * We do NOT run main() directly because it requires user input.
     */
    @Test
    public void testMain() {

        assertNotNull(ConsoleSystem.input);
    }
}
