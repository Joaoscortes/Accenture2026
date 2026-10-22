package io.altar.auth_example.util;

import org.mindrot.jbcrypt.BCrypt;

// https://github.com/patrickfav/bcrypt
// https://www.baeldung.com/java-password-hashing-bcrypt
// https://www.mindrot.org/projects/jBCrypt/
public class PasswordUtil {
    
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}