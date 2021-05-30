package com.example.demo_scsoft.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import com.example.demo_scsoft.MainActivity
import com.example.demo_scsoft.R

class FragmentProfile : Fragment() {
    lateinit var toolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.content, AlbumFragment())
                ?.disallowAddToBackStack()?.commit()
        }
        return view
    }
}