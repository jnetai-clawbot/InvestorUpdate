package com.jnetai.investorupdate.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jnetai.investorupdate.data.Converters

@Entity(tableName = "investors")
@TypeConverters(Converters::class)
data class Investor(
    val name: String = "",
    val firm: String = "",
    val email: String = "",
    val investmentAmount: Double = 0.0,
    val stage: FundingStage = FundingStage.values()[0],
    val lastContactDate: String = "",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

enum class InvestorStage(val label: String) {
    LEAD("Lead"),
    PROSPECT("Prospect"),
    ACTIVE("Active"),
    EXITED("Exited")
}

enum class FundingStage(val label: String) {
    SEED("Seed"),
    SERIES_A("Series A"),
    SERIES_B("Series B"),
    GROWTH("Growth")
}
