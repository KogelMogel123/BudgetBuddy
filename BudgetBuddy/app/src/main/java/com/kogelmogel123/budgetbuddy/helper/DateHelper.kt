package com.kogelmogel123.budgetbuddy.helper

import android.content.Context
import com.kogelmogel123.budgetbuddy.R
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

    fun getLocalizedName(context: Context, month: Month): String {
        val polishMonthNames = mapOf(
            Month.JANUARY to context.getString(R.string.month_january),
            Month.FEBRUARY to context.getString(R.string.month_february),
            Month.MARCH to context.getString(R.string.month_march),
            Month.APRIL to context.getString(R.string.month_april),
            Month.MAY to context.getString(R.string.month_may),
            Month.JUNE to context.getString(R.string.month_june),
            Month.JULY to context.getString(R.string.month_july),
            Month.AUGUST to context.getString(R.string.month_august),
            Month.SEPTEMBER to context.getString(R.string.month_september),
            Month.OCTOBER to context.getString(R.string.month_october),
            Month.NOVEMBER to context.getString(R.string.month_november),
            Month.DECEMBER to context.getString(R.string.month_december)
        )

        return polishMonthNames[month] ?: "Nieznany miesiąc"
    }
}