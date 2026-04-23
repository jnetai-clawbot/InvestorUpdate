package com.jnetai.investorupdate.data

import androidx.room.TypeConverter
import com.jnetai.investorupdate.model.InvestorStage
import com.jnetai.investorupdate.model.FundingStage

class Converters {
    @TypeConverter fun fromInvestorStage(v: InvestorStage): String = v.name
    @TypeConverter fun toInvestorStage(v: String): InvestorStage = InvestorStage.valueOf(v)
    @TypeConverter fun fromFundingStage(v: FundingStage): String = v.name
    @TypeConverter fun toFundingStage(v: String): FundingStage = FundingStage.valueOf(v)
}