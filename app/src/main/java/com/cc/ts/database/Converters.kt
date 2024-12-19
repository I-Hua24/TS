package com.cc.ts.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.Duration

class Converters {

    // For LocalDate
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDate(date: java.time.LocalDate?): String? {
        return date?.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(dateString: String?): java.time.LocalDate? {
        return dateString?.let { java.time.LocalDate.parse(it, formatter) }
    }

    // For Duration
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromDuration(duration: Duration?): Long? {
        return duration?.toMillis()  // Convert Duration to milliseconds for storage
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toDuration(durationMillis: Long?): Duration? {
        return durationMillis?.let { Duration.ofMillis(it) }  // Convert back to Duration
    }
}

