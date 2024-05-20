package com.novawallet.shared;

import org.springframework.security.crypto.bcrypt.*;

public abstract class Bcrypt {

    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(BCryptPasswordEncoder encoder, String pass) {
        return encoder.encode(pass);
    }

    public static boolean verify(BCryptPasswordEncoder encoder,String input, String storedHash) {
        return encoder.matches(input, storedHash);
    }
}