package com.jnetai.investorupdate.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jnetai.investorupdate.InvestorUpdateApp
import com.jnetai.investorupdate.R
import com.jnetai.investorupdate.model.Investor
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: com.jnetai.investorupdate.databinding.ActivityMainBinding
    private val app get() = application as InvestorUpdateApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.jnetai.investorupdate.databinding.ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Investor Update Tracker"

        val adapter = ItemAdapter { item -> startActivity(Intent(this, DetailActivity::class.java).putExtra("item_id", item.id)) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            val items = app.database.dao().getAll()
            adapter.items = items; adapter.notifyDataSetChanged()
            binding.emptyView.visibility = if (items.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }

        binding.fab.setOnClickListener { startActivity(Intent(this, AddActivity::class.java)) }
    }

    override fun onResume() { super.onResume(); refreshList() }

    private fun refreshList() {
        lifecycleScope.launch {
            val items = app.database.dao().getAll()
            (binding.recyclerView.adapter as ItemAdapter).items = items; (binding.recyclerView.adapter as ItemAdapter).notifyDataSetChanged()
            binding.emptyView.visibility = if (items.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { menuInflater.inflate(R.menu.menu_main, menu); return true }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_about -> { startActivity(Intent(this, AboutActivity::class.java)); true }
        else -> super.onOptionsItemSelected(item)
    }
}