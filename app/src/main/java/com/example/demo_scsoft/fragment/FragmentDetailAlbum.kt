package com.example.demo_scsoft.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import com.example.demo_scsoft.R

class FragmentDetailAlbum : Fragment() {
    lateinit var toolbar: Toolbar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_detail_album, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener { startActivity(Intent(context, Activity::class.java)) }
        return view
    }
}