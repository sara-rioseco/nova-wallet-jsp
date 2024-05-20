package com.novawallet.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BcryptTest {

    @Mock
    private BCryptPasswordEncoder mockEncoder = mock(BCryptPasswordEncoder.class);

    @BeforeEach
    void setUp() {
        when(mockEncoder.encode(anyString())).thenReturn("encodedHash");
        when(mockEncoder.matches(anyString(), eq("encodedHash"))).thenReturn(true);
    }

    @Test
    void encodeTest() {
        String result = Bcrypt.encode(mockEncoder,"testPassword");
        assertEquals("encodedHash", result);
    }

    @Test
    void verifyTest() {
        boolean result = Bcrypt.verify(mockEncoder, "testPassword", "encodedHash");
        assertTrue(result);
    }


}