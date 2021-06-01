package com.example.demo_scsoft.model_getlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AlbumType(
    @SerializedName("id")
    @Expose
    var id: Integer,
    @SerializedName("title")
    @Expose
    var title: String,
)