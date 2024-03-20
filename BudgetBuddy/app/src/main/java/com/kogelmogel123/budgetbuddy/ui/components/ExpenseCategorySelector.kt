package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory

@Composable
fun ExpenseCategorySelector(selectedCategory: ExpenseCategory?, onCategorySelected: (ExpenseCategory) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val categories = ExpenseCategory.values()

    Column {
        OutlinedButton(
            onClick = { expanded = true }
        ) {
            Text(text = selectedCategory?.name ?: "Select a category")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                ) {
                    Text(text = category.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExpenseCategorySelector() {
    var selectedCategory by remember { mutableStateOf<ExpenseCategory?>(null) }

    MaterialTheme {
        ExpenseCategorySelector(selectedCategory = selectedCategory) { category ->
            selectedCategory = category
        }
    }
}