package com.example.demo_scsoft.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.demo_scsoft.MainActivity
import com.example.demo_scsoft.R

class FragmentCompany : Fragment() {
    lateinit var toolbar: Toolbar
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_company, container, false)
        toolbar =view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener { v: View? -> onBackPressed() }
        return view
    }
    private fun onBackPressed() {
        startActivity(Intent(context,MainActivity::class.java))
    }
}


