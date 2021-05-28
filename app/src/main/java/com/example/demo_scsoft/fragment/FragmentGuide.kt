package com.example.demo_scsoft.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.MainActivity
import com.example.demo_scsoft.R
import com.example.demo_scsoft.adapter.GuideAdapter

class FragmentGuide : Fragment() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_guide, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recyclerView)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener { startActivity(Intent(context, MainActivity::class.java))
            fragmentManager?.beginTransaction()?.remove(FragmentDetailAlbum())?.commitAllowingStateLoss();}
        testView()
        return view
    }
    @SuppressLint("WrongConstant")
    fun testView(){
        var list = arrayListOf<String>("Guideline test 1", "Guideline test 2", "Guideline test 3", "Guideline test 4", "Guideline test 5")
        recyclerView.layoutManager = LinearLayoutManager(context, 1, false)
        recyclerView.adapter = GuideAdapter(list)
    }
}