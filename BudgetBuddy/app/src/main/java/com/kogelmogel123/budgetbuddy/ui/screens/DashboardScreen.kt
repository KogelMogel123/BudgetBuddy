package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen() {
    Text(text = "Dashboard", style = MaterialTheme.typography.titleLarge)
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}