package com.example.demo_scsoft.model_getlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserCreatedData(
    @SerializedName("id")
    @Expose
    var id: Integer,
    @SerializedName("full_name")
    @Expose
    var fullName: String,
    @SerializedName("email")
    @Expose
    var email: String,
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String,
)