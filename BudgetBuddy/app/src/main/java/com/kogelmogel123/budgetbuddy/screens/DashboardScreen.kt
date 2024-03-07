package com.kogelmogel123.budgetbuddy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DashboardScreen(onClick: (String) -> Unit) {
    Column {
        Text(text = "Dashboard Screen")
        Button(onClick = { onClick("scanReceipt") }) {
            Text(text = "Scan Receipt")
        }
        Button(onClick = { onClick("addExpenses") }) {
            Text(text = "Add Expenses")
        }
    }
}