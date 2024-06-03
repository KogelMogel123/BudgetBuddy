package com.kogelmogel123.budgetbuddy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Expense(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "cost")
    val cost: Double = 0.00,

    @ColumnInfo(name = "category")
    val category: ExpenseCategory = ExpenseCategory.OTHER,

    @ColumnInfo(name = "creation_date")
    val dateAdded: Date,
    
    @ColumnInfo(name = "budget_id")
    val budgetId: Int
)