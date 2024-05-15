package com.kogelmogel123.budgetbuddy.helper

import java.time.Month
import java.util.Calendar
import java.util.Date

object DateHelper {
    fun getMonthDateRange(year: Int, month: Month): Pair<Date, Date> {
        val calendar = Calendar.getInstance()

        // Ustaw początek miesiąca
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month.value - 1)  // Miesiące w Calendar są 0-indeksowane
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val startDate = calendar.time

        // Ustaw koniec miesiąca
        calendar.add(Calendar.MONTH, 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val endDate = calendar.time

        return Pair(startDate, endDate)
    }
}