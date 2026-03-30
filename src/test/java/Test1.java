/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Heave
 */
import com.mycompany.assignment1.Login;
public class Test1 {
    Login testAccount = new Login();
    public Test1() {
    }
    
    @Test
    public void testCheckUsername(){
        assertTrue(testAccount.checkUserName("kyl_1"));
        assertFalse(testAccount.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testPasswordComplexity(){
        assertTrue(testAccount.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(testAccount.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckPhoneNumber(){
        assertTrue(testAccount.checkCellPhoneNumber("+27838968976"));
        assertFalse(testAccount.checkCellPhoneNumber("0838968976"));
    }
    
    @Test
    public void testRegisterUser(){
        assertEquals("Username and password are correctly formatted,\nand the user has successfully been registered.", 
                testAccount.registerUser(true, true, true));
        
        assertEquals("Username is incorrectly formatted.\nPlease ensure that your username contains a underscore,\n and is no more than five characters in length.", 
                testAccount.registerUser(false, true, true));
        
        assertEquals("Password is not correctly formatted.\nPlease ensure that the password contains an uppercase letter,\na digt, a special character, and is at least eight characters in length.", 
                testAccount.registerUser(true, false, true));
        
        assertEquals("Cell number is incorrectly formatted or does not have an international code.\nPlease correct the number and try again.", 
                testAccount.registerUser(true, true, false));
    }
    
    @Test
    public void testLoginUser(){
        assertTrue(testAccount.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertFalse(testAccount.loginUser("kyle!!!!!!!", "password"));
    }
}
