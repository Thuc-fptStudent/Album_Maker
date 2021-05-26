package com.example.demo_scsoft.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toolbar
import com.example.demo_scsoft.MainActivity
import com.example.demo_scsoft.R

class FragmentSetting : Fragment() {
    lateinit var toolbar: Toolbar
    lateinit var settingSizeImage : LinearLayout
    lateinit var chooseLanguage : LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_setting, container, false)
        tollBar(view)
        settingSizeImage(view)
        chooseLanguage(view)

        return view
    }

    fun chooseLanguage(view : View){
        chooseLanguage = view.findViewById(R.id.idChooseLanguage)
        chooseLanguage.setOnClickListener {
            var dialog = AlertDialog.Builder(context)
            dialog.setView(LayoutInflater.from(context).inflate(R.layout.item_choose_language, null))
            dialog.setPositiveButton("OK",{dialog, which ->
                //enter code
            })
            dialog.setNegativeButton("Hủy", {dialog, which ->
                dialog.dismiss()
            })
            dialog.show()
        }
    }
    fun settingSizeImage(view: View){
        settingSizeImage = view.findViewById(R.id.idSizeImage)
        settingSizeImage.setOnClickListener {
            var dialog = AlertDialog.Builder(context)
            dialog.setView(LayoutInflater.from(context).inflate(R.layout.item_set_size_image, null))
            dialog.setPositiveButton("OK", {dialog, wich ->
                // enter code
            })
            dialog.setNegativeButton("Hủy",{dialog, wich->
                dialog.dismiss()
            })
            dialog.show()
        }
    }
    fun tollBar(view: View){
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_west_24)
        toolbar.setNavigationOnClickListener { v: View? -> startActivity(Intent(context,MainActivity::class.java)) }

    }
}