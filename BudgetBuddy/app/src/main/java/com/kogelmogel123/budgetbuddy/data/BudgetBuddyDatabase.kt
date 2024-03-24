package com.kogelmogel123.budgetbuddy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kogelmogel123.budgetbuddy.dao.UserDao
import com.kogelmogel123.budgetbuddy.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class BudgetBuddyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: BudgetBuddyDatabase? = null

        fun getDatabase(context: Context): BudgetBuddyDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BudgetBuddyDatabase::class.java, "budget_buddy_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}
