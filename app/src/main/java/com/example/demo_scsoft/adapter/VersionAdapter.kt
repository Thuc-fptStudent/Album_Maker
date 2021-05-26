package com.example.demo_scsoft.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.R

class VersionAdapter(var list:List<String>) : RecyclerView.Adapter<VersionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv = itemView.findViewById<TextView>(R.id.tvVersion)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_version, null))
    }

    override fun onBindViewHolder(holder: VersionAdapter.ViewHolder, position: Int) {
        holder.tv.setText(list.get(position))
    }

    override fun getItemCount(): Int = list.size
}