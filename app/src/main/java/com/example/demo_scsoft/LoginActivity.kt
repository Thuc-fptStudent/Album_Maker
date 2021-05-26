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
        checkPermistion()
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

    fun checkPermistion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.INTERNET
                ) == PackageManager.PERMISSION_DENIED
            ) {
                requestPermissions(arrayOf(Manifest.permission.INTERNET), 9999)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 9999) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "Không thể kết nối internet", Toast.LENGTH_SHORT).show()
            }
        }
    }
}