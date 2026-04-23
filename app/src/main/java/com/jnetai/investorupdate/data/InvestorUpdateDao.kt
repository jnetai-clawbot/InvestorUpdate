package com.jnetai.investorupdate.data

import androidx.room.*
import com.jnetai.investorupdate.model.Investor

@Dao
interface InvestorUpdateDao {
    @Query("SELECT * FROM investors ORDER BY createdAt DESC") suspend fun getAll(): List<Investor>
    @Insert suspend fun insert(item: Investor): Long
    @Update suspend fun update(item: Investor)
    @Delete suspend fun delete(item: Investor)
}