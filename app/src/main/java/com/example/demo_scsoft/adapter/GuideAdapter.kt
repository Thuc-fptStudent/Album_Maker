package com.example.demo_scsoft.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.R

class GuideAdapter(var list: List<String>) :RecyclerView.Adapter<GuideAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle : TextView = itemView.findViewById(R.id.tvGuideTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_guide, null))
    }

    override fun onBindViewHolder(holder: GuideAdapter.ViewHolder, position: Int) {
        holder.tvTitle.setText(list.get(position))
    }

    override fun getItemCount(): Int = list.size
}