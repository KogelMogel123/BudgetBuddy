package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kogelmogel123.budgetbuddy.helper.DateHelper
import com.kogelmogel123.budgetbuddy.model.Budget
import java.time.Month

@Composable
fun BudgetItemComponent(budget: Budget){
    val context = LocalContext.current
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${DateHelper.getLocalizedName(context, budget.month)} ${budget.year}", style = MaterialTheme.typography.titleMedium)
                Text(text = "${String.format("%.2f", budget.amount)} zł", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview
@Composable
private fun BudgetItemComponentPreview() {
    val budget = Budget(1, Month.JUNE, 2024, 2500.00)
    androidx.compose.material.MaterialTheme {
        BudgetItemComponent(budget);
    }
}