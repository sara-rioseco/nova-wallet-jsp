package com.novawallet.shared;

import org.springframework.security.crypto.bcrypt.*;

public abstract class Bcrypt {

    public static String encode(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }

    public static boolean verify(String input, String storedHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(input, storedHash);
    }
}