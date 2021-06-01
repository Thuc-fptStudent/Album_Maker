package com.example.demo_scsoft.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.demo_scsoft.LoginActivity
import com.example.demo_scsoft.MainActivity
import com.example.demo_scsoft.R
import com.example.demo_scsoft.callback.Click
import com.example.demo_scsoft.callback.ItemClick
import java.util.prefs.Preferences

class FragmentSetting(var click: Click) : Fragment() {
    lateinit var toolbar: Toolbar
    lateinit var settingSizeImage: LinearLayout
    lateinit var chooseLanguage: LinearLayout
    lateinit var btnGiongNs: Switch
    lateinit var tvLanguage: TextView
    lateinit var tvSize: TextView
    lateinit var idSaveChanged: Button
    lateinit var btnLogWithFingerprint: Switch
    lateinit var logOut: Button
    var NAME_SETTING = "SETTING"
    lateinit var preferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_setting, container, false)
        tollBar(view)
        settingSizeImage(view)
        chooseLanguage(view)
        SaveChanged(view)
        logOut(view)
        return view
    }

    fun logOut(view: View,) {
        logOut = view.findViewById(R.id.logOut)
        logOut.setOnClickListener {
            var pr : ProgressDialog = ProgressDialog(context)
            pr.setMessage("Đăng Xuất")
            pr.show()
            Handler().postDelayed({
                click.onClick("logout")
                pr.dismiss()
            }, 1000)
        }
    }

    fun chooseLanguage(view: View) {
        preferences = context?.getSharedPreferences(NAME_SETTING, Context.MODE_PRIVATE)!!
        chooseLanguage = view.findViewById(R.id.idChooseLanguage)
        tvLanguage = view.findViewById(R.id.tvLanguage)
        if (preferences.getString("Language", R.string.vietnamese.toString())
                .equals("Tiếng Việt")
        ) {
            tvLanguage.setText(R.string.vietnamese)
        }
        if (preferences.getString("Language", R.string.vietnamese.toString())
                .equals("日本語")
        ) {
            tvLanguage.setText(R.string.japan)
        }
        if (preferences.getString("Language", R.string.vietnamese.toString())
                .equals("Tiếng Anh")
        ) {
            tvLanguage.setText(R.string.english)
        }
        chooseLanguage.setOnClickListener {
            var dialog = AlertDialog.Builder(context)
            var view: View =
                LayoutInflater.from(context).inflate(R.layout.item_choose_language, null)
            var rbtnViet: RadioButton = view.findViewById(R.id.vietnamese)
            var rbtnAnh: RadioButton = view.findViewById(R.id.english)
            var rbtnNhat: RadioButton = view.findViewById(R.id.japanese)
            if (preferences.getString("Language", R.string.vietnamese.toString())
                    .equals("Tiếng Việt")
            ) {
                rbtnViet.setChecked(true)
            }
            if (preferences.getString("Language", R.string.vietnamese.toString())
                    .equals("日本語")
            ) {
                rbtnNhat.setChecked(true)
            }
            if (preferences.getString("Language", R.string.vietnamese.toString())
                    .equals("Tiếng Anh")
            ) {
                rbtnAnh.setChecked(true)
            }
            dialog.setPositiveButton("OK", { dialog, which ->
                if (rbtnViet.isChecked) {
                    tvLanguage.setText(R.string.vietnamese)
                }
                if (rbtnAnh.isChecked) {
                    tvLanguage.setText(R.string.english)
                }
                if (rbtnNhat.isChecked) {
                    tvLanguage.setText(R.string.japan)
                }
                dialog.dismiss()
            })
            dialog.setNegativeButton("Hủy", { dialog, which ->
                dialog.dismiss()
            })
            dialog.setView(view)
            dialog.show()
        }
    }

    fun settingSizeImage(view: View) {
        preferences = context?.getSharedPreferences(NAME_SETTING, Context.MODE_PRIVATE)!!
        settingSizeImage = view.findViewById(R.id.idSizeImage)
        tvSize = view.findViewById(R.id.idSize)
        if (preferences.getString("Size", "1MB").equals("1MB")) {
            tvSize.setText("1MB")
        }
        if (preferences.getString("Size", "1MB").equals("2MB")) {
            tvSize.setText("2MB")
        }
        if (preferences.getString("Size", "1MB").equals("3MB")) {
            tvSize.setText("3MB")
        }
        settingSizeImage.setOnClickListener {
            var dialog = AlertDialog.Builder(context)
            var view = LayoutInflater.from(context).inflate(R.layout.item_set_size_image, null)
            var id1mb = view.findViewById<RadioButton>(R.id.id1mb)
            var id2mb = view.findViewById<RadioButton>(R.id.id2mb)
            var id3mb = view.findViewById<RadioButton>(R.id.id3mb)
            if (preferences.getString("Size", "1MB").equals("1MB")) {
                id1mb.setChecked(true)
            }
            if (preferences.getString("Size", "1MB").equals("2MB")) {
                id2mb.setChecked(true)
            }
            if (preferences.getString("Size", "1MB").equals("3MB")) {
                id3mb.setChecked(true)
            }
            dialog.setPositiveButton("OK", { dialog, wich ->
                if (id1mb.isChecked) {
                    tvSize.setText("1MB")
                }
                if (id2mb.isChecked) {
                    tvSize.setText("2MB")
                }
                if (id3mb.isChecked) {
                    tvSize.setText("3MB")
                }
            })
            dialog.setNegativeButton("Hủy", { dialog, wich ->
                dialog.dismiss()
            })
            dialog.setView(view)
            dialog.show()
        }
    }

    fun SaveChanged(view: View) {
        var editor = preferences.edit()
        idSaveChanged = view.findViewById(R.id.idSaveChanged)
        idSaveChanged.setOnClickListener {
            editor.putString("Size", tvSize.text.toString())
            editor.putString("Language", tvLanguage.text.toString())
            editor.apply()
            editor.commit()
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
            fragmentManager?.beginTransaction()?.replace(R.id.content, AlbumFragment())
                ?.disallowAddToBackStack()?.commit()
        }
    }

    fun tollBar(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.content, AlbumFragment())
                ?.disallowAddToBackStack()?.commit()
        }

    }
}