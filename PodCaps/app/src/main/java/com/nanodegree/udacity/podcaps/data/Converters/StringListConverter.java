package com.nanodegree.udacity.podcaps.data.Converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConverter {
    @TypeConverter
    public List<String> storedStringToList(String value) {
        return Arrays.asList(value.split("\\s*,\\s*"));
    }

    @TypeConverter
    public String listToStoredString(List<String> favoritedBy) {
        StringBuilder value = new StringBuilder();

        for (String email : favoritedBy)
            value.append(email).append(",");

        return value.toString();
    }
}
