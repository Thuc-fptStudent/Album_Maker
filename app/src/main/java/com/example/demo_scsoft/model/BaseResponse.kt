package com.example.demo_scsoft.model

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("code")
    var code: Int = HttpCode.UNKNOWN_ERROR
    @SerializedName("message")
    var message: String? = null
}