package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.layout.Column
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory

@Composable
fun ExpenseCategorySelectorComponent(selectedCategory: ExpenseCategory?, onCategorySelected: (ExpenseCategory) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val categories = ExpenseCategory.values()
    val context = LocalContext.current

    Column {
        OutlinedButton(
            onClick = { expanded = true }
        ) {
            Text(text = selectedCategory?.getLocalizedName(context) ?: stringResource(id = R.string.select_category))
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
                    Text(text = category.getLocalizedName(context))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseCategorySelectorComponentPreview() {
    var selectedCategory by remember { mutableStateOf<ExpenseCategory?>(null) }

    MaterialTheme {
        ExpenseCategorySelectorComponent(selectedCategory = selectedCategory) { category ->
            selectedCategory = category
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseCategorySelectorComponent2Preview() {
    var selectedCategory by remember { mutableStateOf<ExpenseCategory?>(null) }
    selectedCategory = ExpenseCategory.FOOD

    MaterialTheme {
        ExpenseCategorySelectorComponent(selectedCategory = selectedCategory) { category ->
            selectedCategory = category
        }
    }
}