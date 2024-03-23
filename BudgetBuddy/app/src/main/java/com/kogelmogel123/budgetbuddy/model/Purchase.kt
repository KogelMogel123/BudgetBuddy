package com.kogelmogel123.budgetbuddy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.time.LocalDate

@Entity
data class Purchase(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "cost")
    val cost: Double = 0.00,

    @ColumnInfo(name = "creation_date")
    val creationDate: LocalDate = LocalDate.now()
)