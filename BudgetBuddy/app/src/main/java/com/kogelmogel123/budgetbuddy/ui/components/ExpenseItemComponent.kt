package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory

@Composable
fun ExpenseItemComponent(expense: Expense, onEdit: () -> Unit, onDelete: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { expanded = true }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = expense.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${String.format("%.2f", expense.cost)} zł", style = MaterialTheme.typography.bodyMedium)
            Text(text = "${expense.category}", style = MaterialTheme.typography.bodySmall)
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = {
                onEdit()
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text("Delete") },
            onClick = {
                onDelete()
                expanded = false
            }
        )
    }
}

@Preview
@Composable
private fun ExpenseItemComponentPreview() {
    val expense = Expense(1, "test", 1.00, ExpenseCategory.OTHER, 1)
    androidx.compose.material.MaterialTheme {
        ExpenseItemComponent(expense, {}, {});
    }
}