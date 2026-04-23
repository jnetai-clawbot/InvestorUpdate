package com.jnetai.investorupdate

import android.app.Application
import com.jnetai.investorupdate.data.InvestorUpdateDatabase

class InvestorUpdateApp : Application() {
    val database by lazy { InvestorUpdateDatabase.getInstance(this) }
}