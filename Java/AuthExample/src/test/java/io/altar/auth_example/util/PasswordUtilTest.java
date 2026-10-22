package io.altar.auth_example.util;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordUtilTest {
    
    @Test
    public void testPasswordHashing() {
        String password = "testPassword123";
        
        // Hash the password
        String hashedPassword = PasswordUtil.hashPassword(password);
        
        // Verify that the original password matches the hashed password
        assertTrue(PasswordUtil.verifyPassword(password, hashedPassword));
        
        // Verify that a wrong password does not match
        assertFalse(PasswordUtil.verifyPassword("wrongPassword", hashedPassword));
    }
    
    @Test
    public void testDifferentHashesForSamePassword() {
        String password = "testPassword123";
        
        // Hash the same password multiple times
        String hashedPassword1 = PasswordUtil.hashPassword(password);
        String hashedPassword2 = PasswordUtil.hashPassword(password);
        
        // Verify that different hashes are generated (because of salt)
        assertNotEquals(hashedPassword1, hashedPassword2);
        
        // But both should verify correctly
        assertTrue(PasswordUtil.verifyPassword(password, hashedPassword1));
        assertTrue(PasswordUtil.verifyPassword(password, hashedPassword2));
    }
}