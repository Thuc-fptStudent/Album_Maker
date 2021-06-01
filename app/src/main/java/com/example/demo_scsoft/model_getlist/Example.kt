package com.example.demo_scsoft.model_getlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example(
    @SerializedName("code")
    @Expose
    var code: Integer,
    @SerializedName("data")
    @Expose
    var data: Data,
)