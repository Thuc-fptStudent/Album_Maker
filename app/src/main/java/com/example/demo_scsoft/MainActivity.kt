package com.example.demo_scsoft

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.adapter.AlbumAdapter
import com.example.demo_scsoft.fragment.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var openDrawerNavigation: ImageButton
    lateinit var animation: Animation
    lateinit var fragmentManager: FragmentManager
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        onclick()
        testList()
    }

    @SuppressLint("WrongConstant")
    fun testList() {
        var list = arrayListOf<String>(
            "sdajf",
            "ádfa",
            "adsfasdf",
            "ádfa",
            "adsfasdf",
            "ádfa",
            "adsfasdf",
            "ádfa",
            "adsfasdf",
            "ádfa",
            "adsfasdf",
            "ádfa",
            "adsfasdf",
            "ádfa",
            "adsfasdf",
            "ádfa",
            "adsfasdf"
        )
        var albumAdapter = AlbumAdapter(list)
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
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation_drawer)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        openDrawerNavigation = findViewById(R.id.idDrawerNavigation)
        recyclerView = findViewById(R.id.recyclerViewHome)
        fragmentManager = supportFragmentManager
    }

    fun onclick() {
        openDrawerNavigation.setOnClickListener { drawerLayout.openDrawer(navigationView) }
        navigationView.setNavigationItemSelectedListener { item -> onNavigationItemSelected(item) }
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
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
