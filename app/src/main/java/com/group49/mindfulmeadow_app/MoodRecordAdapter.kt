package com.group49.mindfulmeadow_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoodRecordAdapter(
    private val moodRecords: List<MoodRecord>,
    private val onClick: (MoodRecord) -> Unit
) : RecyclerView.Adapter<MoodRecordAdapter.MoodRecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodRecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mood_record_item, parent, false)
        return MoodRecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodRecordViewHolder, position: Int) {
        val moodRecord = moodRecords[position]
        holder.bind(moodRecord)
        holder.itemView.setOnClickListener { onClick(moodRecord) }
    }

    override fun getItemCount(): Int = moodRecords.size

    class MoodRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        private val tvFeeling: TextView = itemView.findViewById(R.id.tv_feeling)

        fun bind(moodRecord: MoodRecord) {
            tvDate.text = moodRecord.date
            tvFeeling.text = moodRecord.feeling
        }
    }
}