package com.jnetai.investorupdate.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jnetai.investorupdate.InvestorUpdateApp
import com.jnetai.investorupdate.model.Investor
import com.jnetai.investorupdate.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val app get() = application as InvestorUpdateApp
    private var item: Investor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemId = intent.getLongExtra("item_id", -1)
        lifecycleScope.launch {
            val items = app.database.dao().getAll()
            item = items.find { it.id == itemId }
            item?.let { showDetail(it) }
        }

        binding.deleteButton.setOnClickListener {
            item?.let { AlertDialog.Builder(this).setTitle("Delete?").setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch { app.database.dao().delete(it); finish() }
            }.setNegativeButton("Cancel", null).show() }
        }
    }

    private fun showDetail(i: Investor) {
        binding.titleText.text = i.name
        binding.detailText.text = "${i.stage.label} · ${i.firm}"
        binding.notesText.text = i.notes.ifEmpty { "No notes" }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
