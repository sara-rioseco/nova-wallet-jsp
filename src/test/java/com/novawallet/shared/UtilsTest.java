package com.novawallet.shared;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getInitial() {
        String string = "test";
        assertEquals("T", Utils.getInitial(string));
    }

    @Test
    void capitalize() {
        String string = "test";
        assertEquals("Test", Utils.capitalize(string));
    }

    @Test
    void formatDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String date = Utils.formatDate(timestamp);
        assertEquals(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(timestamp.toLocalDateTime()), date);
    }

    @Test
    void formatTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = Utils.formatTime(timestamp);
        assertEquals(DateTimeFormatter.ofPattern("hh:mm a").format(timestamp.toLocalDateTime()), time);
    }
}