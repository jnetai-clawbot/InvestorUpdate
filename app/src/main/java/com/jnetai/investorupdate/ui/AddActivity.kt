package com.jnetai.investorupdate.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jnetai.investorupdate.InvestorUpdateApp
import com.jnetai.investorupdate.databinding.ActivityAddItemBinding
import com.jnetai.investorupdate.model.Investor
import com.jnetai.investorupdate.model.InvestorStage
import com.jnetai.investorupdate.model.FundingStage
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    private val app get() = application as InvestorUpdateApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Add Investor"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.typeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, InvestorStage.values().map { it.label })
        binding.statusSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, FundingStage.values().map { it.label })

        binding.saveButton.setOnClickListener {
            val title = binding.titleEdit.text?.toString()?.trim() ?: ""
            if (title.isBlank()) { Toast.makeText(this, "Title required", Toast.LENGTH_SHORT).show(); return@setOnClickListener }
            lifecycleScope.launch { app.database.dao().insert(Investor(title = title, type = InvestorStage.values()[binding.typeSpinner.selectedItemPosition], status = FundingStage.values()[binding.statusSpinner.selectedItemPosition], notes = binding.notesEdit.text?.toString()?.trim() ?: "")); finish() }
        }
    }
    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}