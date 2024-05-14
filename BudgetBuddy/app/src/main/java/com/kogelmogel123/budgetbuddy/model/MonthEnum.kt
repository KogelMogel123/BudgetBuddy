package com.kogelmogel123.budgetbuddy.model

import android.content.Context
import com.kogelmogel123.budgetbuddy.R

enum class MonthEnum {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;

    fun getLocalizedName(context: Context): String {
        return when (this) {
            JANUARY -> context.getString(R.string.month_january)
            FEBRUARY -> context.getString(R.string.month_february)
            MARCH -> context.getString(R.string.month_march)
            APRIL -> context.getString(R.string.month_april)
            MAY -> context.getString(R.string.month_may)
            JUNE -> context.getString(R.string.month_june)
            JULY -> context.getString(R.string.month_july)
            AUGUST -> context.getString(R.string.month_august)
            SEPTEMBER -> context.getString(R.string.month_september)
            OCTOBER -> context.getString(R.string.month_october)
            NOVEMBER -> context.getString(R.string.month_november)
            DECEMBER -> context.getString(R.string.month_december)
        }
    }
}