package com.example.MovieService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.MovieService.config.JwtUtils;

public class JwtUtilsTest {

    @Test
    void testValidateJwt() {

        JwtUtils jwtUtils = new JwtUtils();

        ReflectionTestUtils.setField(
                jwtUtils,
                "jwtSecret",
                "mysecretkeymysecretkeymysecretkey"
        );

        ReflectionTestUtils.setField(
                jwtUtils,
                "jwtExpirationMs",
                60000
        );

        String token =
                jwtUtils.generateJwtToken("admin@gmail.com");

        assertTrue(jwtUtils.validateJwtToken(token));

        assertEquals(
                "admin@gmail.com",
                jwtUtils.getUserNameFromJwtToken(token)
        );
    }
}
