package com.novawallet.shared;

import org.springframework.security.crypto.bcrypt.*;

/**
 * The type Bcrypt.
 */
public abstract class Bcrypt {

    /**
     * The constant encoder.
     */
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Encode string.
     *
     * @param encoder the encoder
     * @param pass    the pass
     * @return the string
     */
    public static String encode(BCryptPasswordEncoder encoder, String pass) {
        return encoder.encode(pass);
    }

    /**
     * Verify boolean.
     *
     * @param encoder    the encoder
     * @param input      the input
     * @param storedHash the stored hash
     * @return the boolean
     */
    public static boolean verify(BCryptPasswordEncoder encoder,String input, String storedHash) {
        return encoder.matches(input, storedHash);
    }
}