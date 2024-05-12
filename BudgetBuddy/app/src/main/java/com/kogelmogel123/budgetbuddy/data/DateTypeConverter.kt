package com.kogelmogel123.budgetbuddy.data

import androidx.room.TypeConverter
import java.util.Date
class DateTypeConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}