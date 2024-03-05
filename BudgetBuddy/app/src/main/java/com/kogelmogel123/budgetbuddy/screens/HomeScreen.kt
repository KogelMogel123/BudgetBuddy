package com.kogelmogel123.budgetbuddy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(onClick: (String) -> Unit) {
    Column {
        Text(text = "Home Screen")
        Button(onClick = { onClick("scanReceipt") }) {
            Text(text = "Scan Receipt")
        }
        Button(onClick = { onClick("addExpenses") }) {
            Text(text = "Add Expenses")
        }
    }
}