package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScanReceiptScreen() {
    Text(text = "ScanReceipt", style = MaterialTheme.typography.titleLarge)
}

@Preview
@Composable
fun ScanReceiptScreenPreview() {
    ScanReceiptScreen()
}