package com.example.demo_scsoft.fragment

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.R
import com.example.demo_scsoft.adapter.AlbumAdapter
import com.example.demo_scsoft.adapter.SearchHistoryAdapter
import com.example.demo_scsoft.callback.ItemClick
import com.example.demo_scsoft.sqlite.DAO
import com.google.android.material.navigation.NavigationView

class AlbumFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_album, container, false)
        return view
    }
}