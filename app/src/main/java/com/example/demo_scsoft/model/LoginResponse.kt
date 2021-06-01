package com.example.demo_scsoft.model

import android.service.autofill.UserData
import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("data")
    val data: LoginResponseData
) : BaseResponse()

class LoginResponseData(
    @SerializedName("user_token") val token: String,
    @SerializedName("user_data") val userInfo: UserData,
)