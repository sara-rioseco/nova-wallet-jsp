package com.novawallet.shared;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    /**
     * Gets initial.
     *
     * @param word the word
     * @return the initial
     */
    public static String getInitial(String word) {
        return String.valueOf(word.charAt(0)).toUpperCase();
    }

    /**
     * Capitalize string.
     *
     * @param word the word
     * @return the string
     */
    public static String capitalize(String word) {
        return getInitial(word) + word.substring(1);
    }

    /**
     * Format date string.
     *
     * @param timestamp the java.sql timestamp
     * @return the string
     */
    public static String formatDate(Timestamp timestamp){
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return formatDate.format(dateTime);
    }

    /**
     * Format time string.
     *
     * @param timestamp the java.sql timestamp
     * @return the string
     */
    public static String formatTime(Timestamp timestamp){
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("hh:mm a");
        return formatTime.format(dateTime);
    }
}
