package com.example.demo_scsoft.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.demo_scsoft.R

class FragmentCompany() : Fragment() {
    lateinit var toolbar: Toolbar

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_company, container, false)
        toolbar = v?.findViewById(R.id.toolbar)!!
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.content, AlbumFragment())
                ?.disallowAddToBackStack()?.commit()
        }
        return v
    }
}


