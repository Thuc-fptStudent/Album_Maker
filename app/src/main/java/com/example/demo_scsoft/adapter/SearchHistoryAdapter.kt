package com.example.demo_scsoft.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.R
import com.example.demo_scsoft.adapter.SearchHistoryAdapter.*

class SearchHistoryAdapter(var list: List<String>) : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv : TextView = itemView.findViewById(R.id.tv)
        var delete : ImageButton = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.setText(list.get(position))
    }

    override fun getItemCount(): Int = list.size
}