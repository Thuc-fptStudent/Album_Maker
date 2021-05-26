package com.example.demo_scsoft.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.R
import java.nio.file.OpenOption

class NotificationAdapter(var list: List<String>) : RecyclerView.Adapter<NotificationAdapter.Viewholder>() {
    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView2 : TextView = itemView.findViewById(R.id.textView2)
        var imageView3 : ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapter.Viewholder {
        return Viewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_notification, null))
    }

    override fun onBindViewHolder(holder: NotificationAdapter.Viewholder, position: Int) {
        holder.textView2.setText(list.get(position))
        holder.imageView3.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = list.size
}