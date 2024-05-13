package com.kogelmogel123.budgetbuddy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.Expense

@Database(entities = [Expense::class, Budget::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class BudgetBuddyDatabase : RoomDatabase() {
    abstract fun expenseDao(): IExpenseDao
    abstract fun budgetDao(): IBudgetDao

    companion object {
        @Volatile
        private var Instance: BudgetBuddyDatabase? = null

        fun getDatabase(context: Context): BudgetBuddyDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BudgetBuddyDatabase::class.java, "budget_buddy_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}
