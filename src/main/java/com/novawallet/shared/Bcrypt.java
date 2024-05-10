package com.novawallet.shared;

import org.springframework.security.crypto.bcrypt.*;

public abstract class Bcrypt {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String pass) {
        return encoder.encode(pass);
    }

    public static boolean verify(String input, String storedHash) {
        return encoder.matches(input, storedHash);
    }
}