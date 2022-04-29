package com.example.outflearn.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateTimeUtils {

    public static Timestamp timestampOf(LocalDateTime dateTime) {
        return dateTime == null ? null : Timestamp.valueOf(dateTime);
    }

    public static LocalDateTime dateTimeOf(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
