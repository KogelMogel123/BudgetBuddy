package com.kogelmogel123.budgetbuddy.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Expense(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "cost")
    val cost: Double = 0.00,

    @ColumnInfo(name = "category")
    val category: ExpenseCategory = ExpenseCategory.OTHER,

    @ColumnInfo(name = "purchase_id")
    val purchaseId : Int
)