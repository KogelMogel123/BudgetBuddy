package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import okhttp3.internal.format

@Composable
fun ExpenseItem(expense: Expense) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = expense.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${String.format("%.2f", expense.cost)} zł", style = MaterialTheme.typography.bodyMedium)
            Text(text = "${expense.category}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun PreviewExpenseItem() {
    var expense = Expense("name", 1.00, ExpenseCategory.OTHER,1)
    androidx.compose.material.MaterialTheme {
        ExpenseItem(expense);
    }
}