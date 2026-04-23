package com.jnetai.investorupdate.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jnetai.investorupdate.model.Investor
import com.jnetai.investorupdate.databinding.ItemGenericBinding

class ItemAdapter(private val onClick: (Investor) -> Unit) : RecyclerView.Adapter<ItemAdapter.VH>() {
    var items: List<Investor> = emptyList()
    inner class VH(val binding: ItemGenericBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(ItemGenericBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(h: VH, pos: Int) {
        val item = items[pos]
        h.binding.titleText.text = item.name
        h.binding.subtitleText.text = "${item.stage.label} · ${item.firm}"
        h.binding.root.setOnClickListener { onClick(item) }
    }
}
