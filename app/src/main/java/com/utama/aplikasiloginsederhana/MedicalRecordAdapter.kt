package com.utama.aplikasiloginsederhana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicalRecordAdapter(
    private val recordList: List<MedicalRecord>,
    private val onItemClick: (MedicalRecord) -> Unit
) : RecyclerView.Adapter<MedicalRecordAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPatientName: TextView = view.findViewById(R.id.tvItemName)
        val tvExamination: TextView = view.findViewById(R.id.tvItemCategory)
        val tvDate: TextView = view.findViewById(R.id.tvItemUnit)
        val tvResult: TextView = view.findViewById(R.id.tvItemStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = recordList[position]
        holder.tvPatientName.text = record.patientName
        holder.tvExamination.text = record.examination
        holder.tvDate.text = record.date
        holder.tvResult.text = record.result

        holder.itemView.setOnClickListener {
            onItemClick(record)
        }
    }

    override fun getItemCount(): Int = recordList.size
}
