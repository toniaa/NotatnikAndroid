package com.tonia.notatnik;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverters {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toTimestamp(Date value) {
        return value == null ? null : value.getTime();
    }
}
