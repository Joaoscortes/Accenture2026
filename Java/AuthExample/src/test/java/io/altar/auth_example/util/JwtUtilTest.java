package io.altar.auth_example.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.JwtException;

public class JwtUtilTest {

    @Test
    void tokenRoundTripReturnsOwner() {
        String token = JwtUtil.generateToken("alice", "ADMIN", 42);

        assertEquals("alice", JwtUtil.getUsernameFromToken(token));
        assertEquals("ADMIN", JwtUtil.getRoleFromToken(token));
        assertEquals(42L, JwtUtil.getUserIdFromToken(token));
        assertFalse(JwtUtil.isTokenExpired(token));
    }

    @Test
    void tamperedTokenIsRejected() {
        String token = JwtUtil.generateToken("alice", "USER", 42);
        String tampered = token.substring(0, token.length() - 2) + "xx";

        assertThrows(JwtException.class, () -> JwtUtil.getUsernameFromToken(tampered));
    }

    @Test
    void malformedTokenIsRejected() {
        assertThrows(JwtException.class, () -> JwtUtil.getUsernameFromToken("not-a-token"));
    }
}
