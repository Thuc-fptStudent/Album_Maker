package com.example.demo_scsoft.model_getlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data__1(
    @SerializedName("id")
    @Expose
    var id: Integer,
    @SerializedName("image_path")
    @Expose
    var imagePath: String,
    @SerializedName("image_url")
    @Expose
    var imageUrl: String,
    @SerializedName("album_type")
    @Expose
    var albumType: AlbumType,

    @SerializedName("album_informations")
    @Expose
    var albumInformations: List<AlbumInformation> ,

    @SerializedName("user_created_data")
    @Expose
    var userCreatedData: UserCreatedData,

    @SerializedName("show_comment")
    @Expose
    var showComment: Boolean
)