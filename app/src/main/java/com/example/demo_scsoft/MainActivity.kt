package com.example.demo_scsoft

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.DisplayMetrics
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.LinearLayout.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.adapter.AlbumAdapter
import com.example.demo_scsoft.adapter.SearchHistoryAdapter
import com.example.demo_scsoft.api.ApiService
import com.example.demo_scsoft.api.RetrofitClient
import com.example.demo_scsoft.callback.Click
import com.example.demo_scsoft.callback.ItemClick
import com.example.demo_scsoft.fragment.*
import com.example.demo_scsoft.model.LoginResponse
import com.example.demo_scsoft.model_getlist.*
import com.example.demo_scsoft.sqlite.DAO
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


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
    lateinit var searchHistoryAdapter: SearchHistoryAdapter
    lateinit var processBar: ProgressBar
    lateinit var preferences: SharedPreferences
    var listData__1: List<Data__1> = arrayListOf()
    var listAlbumInformation: List<AlbumInformation> = arrayListOf()
    var listAlbumType: MutableList<AlbumType> = mutableListOf()
    open var fragmentManager: FragmentManager = supportFragmentManager
    lateinit var recyclerView: RecyclerView
    var exit: Boolean = false
    lateinit var data: Data
    lateinit var example: Example
    lateinit var data1: Data__1
    lateinit var albumInformations: AlbumInformation
    lateinit var albumType: AlbumType
    var list = arrayListOf(
        "àdsafa",
        "ádgasdg",
        "lidhiihi",
        "sdadjfgadsjop",
        "ddjf adf",
        "ĐÂM",
        "jdaskfj"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        onclick()
        setCurrentFragment(AlbumFragment())
        setLocale()
        getAlbum()
        testList(
            list
        )

        Log.e("TOKEN", "" + RetrofitClient.token.toString())
    }

    fun getAlbum() {
        RetrofitClient.buildService(ApiService::class.java).getAlbum()
            .enqueue(object :
                Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    if (response.isSuccessful) {
                        example = response.body()!!
                        data = example.data
                        listData__1 = data.dataList
                    }
                    Log.e("RESPONSE", "" + response.message())
                }

                override fun onFailure(call: Call<Example>, t: Throwable) {
                    Log.e("ERR", "" + t.message)
                }

            })
    }

    fun setLocale() {
        preferences = this.getSharedPreferences(FragmentSetting(object : Click {
            override fun onClick(string: String) {
            }
        }).NAME_SETTING, Context.MODE_PRIVATE)
        var lang = preferences.getString("Language", "vn").toString()

        Toast.makeText(applicationContext, "" + lang, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("WrongConstant")
    fun testList(list: List<String>) {
        var albumAdapter = AlbumAdapter(list, object : ItemClick {
            override fun setOnItemClick(view: View, position: Int, s: String) {
                setCurrentFragment(FragmentDetailAlbum())
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, 1, false)
        recyclerView.adapter = albumAdapter
        //load data

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
        reLoad = findViewById(R.id.reLoad)
        processBar = findViewById(R.id.progressBar)
        recyclerViewHistorySearch = findViewById(R.id.recyclerViewHistorySearch)
        editSearch = findViewById(R.id.editSearch)
        linearLayoutSeach = findViewById(R.id.line1)
        tvHistory = findViewById(R.id.tvHistory)
        linearLayoutSeach.setVisibility(View.GONE)
        view = findViewById(R.id.view)
        tvHistory.setVisibility(View.GONE)
        processBar.visibility = View.GONE
    }

    fun onclick() {
        openDrawerNavigation.setOnClickListener { drawerLayout.openDrawer(navigationView) }
        navigationView.setNavigationItemSelectedListener { item -> onNavigationItemSelected(item) }
        //open search
        search.setOnClickListener { openSearch() }
        //close search
        close.setOnClickListener {
            openDrawerNavigation.setVisibility(View.VISIBLE)
            linearLayoutSeach.setVisibility(View.GONE)
            view.setVisibility(View.VISIBLE)
            testList(list)
        }
        // search
        editSearch.addTextChangedListener { it -> search(it.toString()) }
        //reload
        reLoad.setOnClickListener { reLoad() }
    }

    fun reLoad() {
        processBar.visibility = View.VISIBLE
        Handler().postDelayed({ processBar.visibility = View.GONE }, 2000)
    }

    @SuppressLint("WrongConstant")
    fun openSearch() {
        openDrawerNavigation.setVisibility(View.INVISIBLE)
        linearLayoutSeach.setVisibility(View.VISIBLE)
        view.setVisibility(View.GONE)
        var list: MutableList<String> = DAO(applicationContext).getAll() as MutableList<String>
        Log.e("Size List: ", "" + list.size)

        if (list.size > 0) {
            tvHistory.setVisibility(View.VISIBLE)
            searchHistoryAdapter = SearchHistoryAdapter(list, object : ItemClick {
                override fun setOnItemClick(view: View, position: Int, s: String) {
                    if (s.equals("itemView")) {
                        editSearch.setText(list.get(position))
                    }
                    if (s.equals("delete")) {
                        var dao: DAO = DAO(applicationContext)
                        dao.delete(list.get(position))
                        openSearch()
                    }
                }
            })
            recyclerViewHistorySearch.layoutManager =
                LinearLayoutManager(applicationContext, HORIZONTAL, false)
            recyclerViewHistorySearch.adapter = searchHistoryAdapter
        }
    }

    @SuppressLint("WrongConstant")
    fun search(s: String) {
        var listSearch = list.filter { it.contains(s, ignoreCase = true) }
        albumAdapter = AlbumAdapter(listSearch, object : ItemClick {
            override fun setOnItemClick(view: View, position: Int, s: String) {
                setCurrentFragment(FragmentDetailAlbum())
                var dao = DAO(applicationContext)
                var result: Int = dao.addString(editSearch.text.toString())
                if (result == 1) {
                    Log.e("INSERT", "OK")
                } else {
                    Log.e("INSERT", "NOT")
                }
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
                setCurrentFragment(FragmentSetting(object : Click {
                    override fun onClick(string: String) {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        finish()
                    }
                }))
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
        fragmentManager.beginTransaction().replace(R.id.content, fragment).disallowAddToBackStack()
            .commit()
    }

    override fun onBackPressed() {
        if (exit) {
            super.onBackPressed()
        } else {
            exit = true
            Toast.makeText(this, "Bấm lần nữa để thoát", Toast.LENGTH_SHORT).show()
        }
        Handler().postDelayed({ exit = false }, 1500)
    }
}

