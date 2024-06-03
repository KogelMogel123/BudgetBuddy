package com.kogelmogel123.budgetbuddy.model

import androidx.room.Embedded
import androidx.room.Relation

data class BudgetWithExpenses(
    @Embedded
    val budget: Budget,
    @Relation(
        parentColumn = "id",
        entityColumn = "budget_id"
    )
    val expenses: List<Expense>
)