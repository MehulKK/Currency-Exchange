package com.macv.currencyconverter.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.macv.currencyconverter.model.data.CurrencyRate

/**
 * The [Room] database for this app.
 */
@Database(
        entities = [CurrencyRate::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyRateDao(): CurrencyDAO

    companion object {
        const val databaseName = "app-database"

        @Volatile
        private var instance: AppDatabase? = null

        /**
         * Build and return [RoomDatabase] instance of the app.
         *
         * @param [context] application context
         *
         * @return [AppDatabase] instance
         */
        fun buildDatabase(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: let {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                        .allowMainThreadQueries()
                        .build()

                instance!!
            }
        }
    }
}
