package com.example.demo_scsoft.model_getlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AlbumInformation(
    @SerializedName("id")
    @Expose
    var id: Integer,
    @SerializedName("album_property_id")
    @Expose
    var albumPropertyId: Integer,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("type")
    @Expose
    var type: Integer,
    @SerializedName("require")
    @Expose
    var require: Integer,
    @SerializedName("display")
    @Expose
    var display: Integer,
    @SerializedName("highlight")
    @Expose
    var highlight: Integer,
    @SerializedName("value")
    @Expose
    var value: String
)