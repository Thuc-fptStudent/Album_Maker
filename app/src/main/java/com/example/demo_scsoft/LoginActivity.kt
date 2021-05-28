package com.example.demo_scsoft

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.demo_scsoft.api.ApiService
import com.example.demo_scsoft.api.RetrofitClient
import com.example.demo_scsoft.model.UserRespon
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var email: AutoCompleteTextView
    lateinit var password: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin = findViewById(R.id.button)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnLogin.setOnClickListener {
            val request = RetrofitClient.buildService(ApiService::class.java)
            val call = request.login(
                "sotdmy",
                email.text.toString().trim(),
                password.text.toString().trim(),
                1,
                ""
            )
            call.enqueue(object : retrofit2.Callback<UserRespon> {
                override fun onResponse(call: Call<UserRespon>, response: Response<UserRespon>) {
                    //code
                }

                override fun onFailure(call: Call<UserRespon>, t: Throwable) {

                }

            })
        }
    }
}