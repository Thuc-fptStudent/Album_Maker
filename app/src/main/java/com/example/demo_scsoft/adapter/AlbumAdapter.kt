package com.example.demo_scsoft.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.R


class AlbumAdapter(var list: List<String>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idCharg: TextView = itemView.findViewById(R.id.idCharg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idCharg.setText(list.get(position))
    }
    override fun getItemCount(): Int = list.size
}