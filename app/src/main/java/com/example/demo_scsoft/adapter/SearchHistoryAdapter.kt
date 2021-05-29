package com.example.demo_scsoft.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.R
import com.example.demo_scsoft.adapter.SearchHistoryAdapter.*
import com.example.demo_scsoft.callback.ItemClick

class SearchHistoryAdapter(var list: List<String>,var itemClick: ItemClick) : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv : TextView = itemView.findViewById(R.id.tv)
        var delete : ImageButton = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.setText(list.get(position))
        holder.delete.setOnClickListener { v-> itemClick.setOnItemClick(v,position,"delete") }
        holder.itemView.setOnClickListener { v-> itemClick.setOnItemClick(v,position,"itemView") }
    }

    override fun getItemCount(): Int = list.size
}