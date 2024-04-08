package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kogelmogel123.budgetbuddy.R

@Composable
fun ScanReceiptScreen() {
    Text(text = stringResource(id = R.string.scanReceipt), style = MaterialTheme.typography.titleLarge)
}

@Preview
@Composable
fun ScanReceiptScreenPreview() {
    ScanReceiptScreen()
}