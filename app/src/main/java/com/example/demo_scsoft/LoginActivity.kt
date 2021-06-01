package com.example.demo_scsoft

import android.R.id.message
import android.R.id.progress
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demo_scsoft.api.ApiService
import com.example.demo_scsoft.api.RetrofitClient
import com.example.demo_scsoft.model.LoginRequest
import com.example.demo_scsoft.model.LoginResponse
import com.example.demo_scsoft.model.LoginResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var email: AutoCompleteTextView
    lateinit var password: AutoCompleteTextView
    lateinit var dialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin = findViewById(R.id.button)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        dialog = ProgressDialog(this)
        btnLogin.setOnClickListener {
            dialog.setMessage("Vui lòng chờ")
            dialog.setTitle("Đăng Nhập")
            dialog.show()
            var request = LoginRequest("sotdmy", email.text.toString(), password.text.toString())
            RetrofitClient.buildService(ApiService::class.java).login(request)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        var thread = Thread(Runnable {
                            kotlin.run {
                                if (response.isSuccessful) {
                                    var log : LoginResponseData = response.body()?.data!!
                                    RetrofitClient.token = log.token
                                    dialog.dismiss()
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            MainActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                            }
                        })
                        thread.start()
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        var s =
                            "Unable to resolve host \"supenient.vn\": No address associated with hostname"
                        var s2 =
                            "Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path \$"
                        if (t.message.equals(s)) {
                            Toast.makeText(
                                applicationContext,
                                "Lỗi kết nối tới máy chủ",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (t.message.equals(s2)) {
                            Toast.makeText(
                                applicationContext,
                                "Tài khoản hoặc mật khẩu không chính xác",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog.dismiss()
                    }
                })
        }
    }

}