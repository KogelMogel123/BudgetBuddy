package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExpenseItemComponent(expense: Expense, onEdit: () -> Unit, onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { expanded = true }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = expense.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "${String.format("%.2f", expense.cost)} zł", style = MaterialTheme.typography.bodyMedium)
                Text(text = "${expense.category.getLocalizedName(context)}", style = MaterialTheme.typography.bodySmall)
                Text(text = formatDate(expense.dateAdded), style = MaterialTheme.typography.bodySmall)
            }
            Icon(
                imageVector = getIconForCategory(expense.category),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
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

@Composable
fun getIconForCategory(category: ExpenseCategory): ImageVector {
    return when (category) {
        ExpenseCategory.GROCERIES -> Icons.Default.Restaurant
        ExpenseCategory.TRANSPORT -> Icons.Default.DirectionsCar
        ExpenseCategory.HOUSING -> Icons.Default.Home
        ExpenseCategory.ENTERTAINMENT -> Icons.Default.Movie
        ExpenseCategory.CLOTHING -> Icons.Default.Checkroom
        ExpenseCategory.EDUCATION -> Icons.Default.School
        ExpenseCategory.OTHER -> Icons.Default.QuestionMark
    }
}

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(date)
}

@Preview
@Composable
private fun ExpenseItemComponentPreview() {
    val expense = Expense(1, "test", 1.00, ExpenseCategory.OTHER, Date())
    androidx.compose.material.MaterialTheme {
        ExpenseItemComponent(expense, {}, {});
    }
}