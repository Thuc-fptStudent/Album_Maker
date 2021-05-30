package com.example.demo_scsoft.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_scsoft.MainActivity
import com.example.demo_scsoft.R
import com.example.demo_scsoft.adapter.NotificationAdapter
import javax.security.auth.callback.Callback

class FragmentNotification : Fragment() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_notification, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewNotification)
        toolbar = view.findViewById(R.id.toolbar)
        imageView = view.findViewById(R.id.imageView)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.content, AlbumFragment())
                ?.disallowAddToBackStack()?.commit()
        }
        testList()
        onclick()
        return view
    }

    @SuppressLint("WrongConstant")
    fun testList() {
        var list =
            arrayListOf<String>("test1", "test2", "test3", "test4", "test5", "test6", "test7")
        var adapter = NotificationAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(context, 1, false)
        recyclerView.adapter = adapter
    }

    fun onclick() {
        imageView.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Bạn có muốn đánh dấu tất cả đã đọc ?")
            builder.setPositiveButton("OK") { dialog, which ->
                // code here
            }
            builder.setNegativeButton("Hủy") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
    }
}