package com.novawallet.app;

import org.springframework.security.crypto.bcrypt.*;

public abstract class Bcrypt {

    protected static String encode(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }

    protected static boolean verify(String input, String storedHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(input, storedHash);
    }
}