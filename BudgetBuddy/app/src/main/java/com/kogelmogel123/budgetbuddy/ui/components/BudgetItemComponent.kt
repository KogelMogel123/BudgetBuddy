package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.helper.DateHelper
import com.kogelmogel123.budgetbuddy.model.Budget
import java.time.Month

@Composable
fun BudgetItemComponent(budget: Budget, totalExpenses: Double = 0.0, spentPercentage: Double = 0.0){
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
                Text(text = "${stringResource(id = R.string.budget_amount)}: ${String.format("%.2f", budget.amount)} zł", style = MaterialTheme.typography.bodyMedium)
                Text(text = "${stringResource(id = R.string.spent_from_budget)}: ${String.format("%.2f", totalExpenses)} zł", style = MaterialTheme.typography.bodyMedium)
                LinearProgressIndicator(
                    progress = { spentPercentage.toFloat() },
                    color = Color(0xFF4CAF50),
                    modifier = Modifier
                        .border(1.dp, Color(0xFF4CAF50))
                        .fillMaxWidth()
                        .height(26.dp)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun BudgetItemComponentPreview() {
    val budget = Budget(1, Month.JUNE, 2024, 2500.00)
    androidx.compose.material.MaterialTheme {
        BudgetItemComponent(budget, 1500.00, 0.6);
    }
}