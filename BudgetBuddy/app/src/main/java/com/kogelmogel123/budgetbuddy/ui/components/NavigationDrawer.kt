package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kogelmogel123.budgetbuddy.MenuItem
import com.kogelmogel123.budgetbuddy.R

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.main))
            .fillMaxWidth()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val imageModifier = Modifier

        Image(
            painter = painterResource(id = R.drawable.buddy),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
        Text(text = "Budget Buddy", fontSize = 25.sp, modifier = Modifier.padding(top = 2.dp))
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(name = "DrawerHeaderPreview", showBackground = true, showSystemUi = true)
@Composable
fun DrawerHeaderPreview() {
    DrawerHeader()
}

@Preview(name = "DrawerBodyPreview", showBackground = true, showSystemUi = true)
@Composable
fun DrawerBodyPreview() {
    DrawerBody(
        items = listOf(
            MenuItem(
                id = "1",
                title = "Dashboard",
                contentDescription = "Dashboard",
                icon = Icons.Default.Home
            ),
            MenuItem(
                id = "2",
                title = "ScanReceipt",
                contentDescription = "ScanReceipt",
                icon = Icons.Default.Search
            ),
            MenuItem(
                id = "3",
                title = "Expenses",
                contentDescription = "Expenses",
                icon = Icons.Default.AccountBalance
            )
        ),
        onItemClick = {}
    )
}