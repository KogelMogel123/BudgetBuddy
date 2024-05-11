package com.kogelmogel123.budgetbuddy.model

import android.content.Context
import com.kogelmogel123.budgetbuddy.R

enum class ExpenseCategory {
    Groceries,
    TRANSPORT,
    HOUSING,
    ENTERTAINMENT,
    CLOTHING,
    EDUCATION,
    OTHER;

    fun getLocalizedName(context: Context): String {
        return when (this) {
            Groceries -> context.getString(R.string.category_groceries)
            TRANSPORT -> context.getString(R.string.category_transport)
            HOUSING -> context.getString(R.string.category_housing)
            ENTERTAINMENT -> context.getString(R.string.category_entertainment)
            CLOTHING -> context.getString(R.string.category_clothing)
            EDUCATION -> context.getString(R.string.category_education)
            OTHER -> context.getString(R.string.category_other)
        }
    }
}