package com.utama.aplikasiloginsederhana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InventoryAdapter(
    private val itemList: List<InventoryItem>,
    private val onItemClick: (InventoryItem) -> Unit
) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvItemName)
        val tvCategory: TextView = view.findViewById(R.id.tvItemCategory)
        val tvStock: TextView = view.findViewById(R.id.tvItemStock)
        val tvUnit: TextView = view.findViewById(R.id.tvItemUnit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.tvName.text = item.name
        holder.tvCategory.text = item.category
        holder.tvStock.text = item.stock.toString()
        holder.tvUnit.text = item.unit

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = itemList.size
}
