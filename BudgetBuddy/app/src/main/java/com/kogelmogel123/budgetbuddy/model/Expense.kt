package com.kogelmogel123.budgetbuddy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "cost")
    val cost: Double = 0.00,

    @ColumnInfo(name = "category")
    val category: ExpenseCategory = ExpenseCategory.OTHER,

    @ColumnInfo(name = "purchase_id")
    val purchaseId : Int
)