package com.kogelmogel123.budgetbuddy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budget(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "month")
    val month: MonthEnum,

    @ColumnInfo(name = "year")
    val year: Int = 2024,

    @ColumnInfo(name = "amount")
    val amount: Double = 0.00
)
