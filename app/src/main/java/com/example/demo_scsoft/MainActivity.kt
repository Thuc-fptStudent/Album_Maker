package com.example.demo_scsoft

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.adapter.AlbumAdapter
import com.example.demo_scsoft.adapter.SearchHistoryAdapter
import com.example.demo_scsoft.callback.ItemClick
import com.example.demo_scsoft.fragment.*
import com.example.demo_scsoft.sqlite.DAO
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var openDrawerNavigation: ImageButton
    lateinit var search: ImageButton
    lateinit var reLoad: ImageButton
    lateinit var close: ImageView
    lateinit var view: View
    lateinit var editSearch: EditText
    lateinit var linearLayoutSeach: LinearLayout
    lateinit var tvHistory: TextView
    lateinit var animation: Animation
    lateinit var albumAdapter: AlbumAdapter
    lateinit var recyclerViewHistorySearch: RecyclerView
    var list = arrayListOf<String>(
        "sdajf",
        "ádfa",
        "adsfasdf",
        "ádfa",
        "adsfasdf",
        "ádfa",
        "adsfasdf",
        "ádfa",
        "Dam van thuc",
        "ádfa",
        "adsfasdf",
        "ádfa",
        "adsfasdf",
        "ádfa",
        "adsfasdf",
        "ádfa",
        "adsfasdf"
    )
    open var fragmentManager: FragmentManager = supportFragmentManager
    lateinit var recyclerView: RecyclerView
    var exit: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        testList()
        onclick()
    }

    @SuppressLint("WrongConstant")
    fun testList() {
        var albumAdapter = AlbumAdapter(list, object : ItemClick {
            override fun setOnItemClick(view: View, position: Int) {
                setCurrentFragment(FragmentDetailAlbum())
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, 1, false)
        recyclerView.adapter = albumAdapter
        //load data
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    fun initView() {
        val window: Window = window
        window.setStatusBarColor(getResources().getColor(R.color.purple_500));
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation_drawer)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        openDrawerNavigation = findViewById(R.id.idDrawerNavigation)
        recyclerView = findViewById(R.id.recyclerViewHome)
        search = findViewById(R.id.seach)
        close = findViewById(R.id.idClose)
        recyclerViewHistorySearch = findViewById(R.id.recyclerViewHistorySearch)
        editSearch = findViewById(R.id.editSearch)
        linearLayoutSeach = findViewById(R.id.line1)
        tvHistory = findViewById(R.id.tvHistory)
        linearLayoutSeach.setVisibility(View.GONE)
        view = findViewById(R.id.view)
        tvHistory.setVisibility(View.GONE)
    }

    @SuppressLint("WrongConstant")
    fun onclick() {
        openDrawerNavigation.setOnClickListener { drawerLayout.openDrawer(navigationView) }
        navigationView.setNavigationItemSelectedListener { item -> onNavigationItemSelected(item) }
        //search
        search.setOnClickListener {
            openDrawerNavigation.setVisibility(View.INVISIBLE)
            linearLayoutSeach.setVisibility(View.VISIBLE)
            view.setVisibility(View.GONE)
//            var list = DAO(applicationContext).getAll()
//            recyclerViewHistorySearch.layoutManager = LinearLayoutManager(applicationContext, DividerItemDecoration.HORIZONTAL, true)
//            recyclerView.adapter = SearchHistoryAdapter(list)
        }
        close.setOnClickListener {
            openDrawerNavigation.setVisibility(View.VISIBLE)
            linearLayoutSeach.setVisibility(View.GONE)
            view.setVisibility(View.VISIBLE)
        }
        editSearch.addTextChangedListener { it -> search(it.toString()) }
    }

    @SuppressLint("WrongConstant")
    fun search(s: String) {
        var listSearch = list.filter { it.contains(s, ignoreCase = true) }
        albumAdapter = AlbumAdapter(listSearch, object : ItemClick {
            override fun setOnItemClick(view: View, position: Int) {
                setCurrentFragment(FragmentDetailAlbum())
                DAO(applicationContext).addString(editSearch.text.toString())
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, 1, false)
        recyclerView.adapter = albumAdapter
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemCompany -> {
                setCurrentFragment(FragmentCompany())
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.itemSetting -> {
                setCurrentFragment(FragmentSetting())
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.itemProfile -> {
                setCurrentFragment(FragmentProfile())
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.itemNotifycation -> {
                setCurrentFragment(FragmentNotification())
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.itemGuide -> {
                setCurrentFragment(FragmentGuide())
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.itemVersion -> {
                setCurrentFragment(FragmentVersion())
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }

    fun setCurrentFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.content, fragment).addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (fragmentManager != null) {
            fragmentManager.isDestroyed
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (exit) {
            super.onBackPressed()
        } else {
            exit = true
            Toast.makeText(this, "Bấm lần nữa để thoát", Toast.LENGTH_SHORT).show()
        }
        Handler().postDelayed({ exit = false }, 1500)
    }

}

