package com.kogelmogel123.budgetbuddy

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kogelmogel123.budgetbuddy.di.appModule
import com.kogelmogel123.budgetbuddy.ui.components.AppBar
import com.kogelmogel123.budgetbuddy.ui.components.DrawerBody
import com.kogelmogel123.budgetbuddy.ui.components.DrawerHeader
import com.kogelmogel123.budgetbuddy.ui.screens.AddExpenseScreen
import com.kogelmogel123.budgetbuddy.ui.screens.DashboardScreen
import com.kogelmogel123.budgetbuddy.ui.screens.EditExpenseScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ExpensesScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ReceiptsAnalysisScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ScanReceiptScreen
import com.kogelmogel123.budgetbuddy.ui.screens.camera.CameraPreviewScreen
import com.kogelmogel123.budgetbuddy.ui.theme.BudgetBuddyTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Koin
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            BudgetBuddyTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "dashboardScreen",
                                    title = stringResource(id = R.string.dashboard_screen),
                                    contentDescription = "Go to dashboard screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "expensesScreen",
                                    title = stringResource(id = R.string.expenses_screen),
                                    contentDescription = "Go to expenses screen",
                                    icon = Icons.Default.AccountBalance
                                ),
                                MenuItem(
                                    id = "scanReceiptScreen",
                                    title = stringResource(id = R.string.scan_receipt_screen),
                                    contentDescription = "Go to scan receipt screen",
                                    icon = Icons.Default.Search
                                ),
                                MenuItem(
                                    id = "ReceiptsAnalysisScreen",
                                    title = stringResource(id = R.string.receipt_analysis_screen),
                                    contentDescription = "Go to receipt analysis screen",
                                    icon = Icons.Default.AutoAwesome
                                ),
                            ),
                            onItemClick = {menuItem ->
                                println("Clicked on ${menuItem.title}")
                                navController.navigate(menuItem.id)
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    }
                ) {
                    NavHost(navController, startDestination = "dashboardScreen") {
                        composable("dashboardScreen") {
                            DashboardScreen()
                        }
                        composable("scanReceiptScreen") {
                            ScanReceiptScreen(navController = navController)
                        }
                        composable("expensesScreen") {
                            ExpensesScreen(navController = navController)
                        }
                        composable("addExpenseScreen") {
                            AddExpenseScreen(navController = navController)
                        }
                        composable("receiptsAnalysisScreen") {
                            ReceiptsAnalysisScreen(navController = navController)
                        }
                        composable(
                            route = "receiptsAnalysisScreen/{selectedImageEncodedUri}",
                            arguments = listOf(navArgument("selectedImageEncodedUri") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val selectedImageEncodedUri = backStackEntry.arguments?.getString("selectedImageEncodedUri")
                            ReceiptsAnalysisScreen(selectedImageEncodedUri = selectedImageEncodedUri, navController = navController)
                        }
                        composable(
                            route = "editExpenseScreen/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val expenseId = backStackEntry.arguments?.getInt("id") ?: -1
                            EditExpenseScreen(navController = navController, id = expenseId)
                        }
                        composable("cameraPreviewScreen") {
                            CameraPreviewScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}