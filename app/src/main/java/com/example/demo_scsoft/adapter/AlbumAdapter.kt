package com.example.demo_scsoft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.MainActivity
import com.example.demo_scsoft.R
import com.example.demo_scsoft.callback.ItemClick
import com.example.demo_scsoft.fragment.FragmentDetailAlbum


class AlbumAdapter(var list: List<String>,var itemClick : ItemClick) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    lateinit var context: Context

    class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idCharg: TextView = itemView.findViewById(R.id.idCharg)
        var itemCardView: CardView = itemView.findViewById(R.id.idCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, null)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idCharg.setText(list.get(position))
        holder.itemCardView.setOnClickListener { v->
            itemClick.setOnItemClick(v, position,"")
        }
    }

    override fun getItemCount(): Int = list.size
}