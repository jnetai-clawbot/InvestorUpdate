package com.jnetai.investorupdate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnetai.investorupdate.model.Investor

@Database(entities = [Investor::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class InvestorUpdateDatabase : RoomDatabase() {
    abstract fun dao(): InvestorUpdateDao
    companion object {
        @Volatile private var INSTANCE: InvestorUpdateDatabase? = null
        fun getInstance(context: Context): InvestorUpdateDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context, InvestorUpdateDatabase::class.java, "investor_db")
                .fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}