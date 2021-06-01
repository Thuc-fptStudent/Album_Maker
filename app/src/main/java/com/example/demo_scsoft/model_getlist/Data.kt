package com.example.demo_scsoft.model_getlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data(
    @SerializedName("data_list")
    @Expose
    var dataList: List<Data__1>,
    @SerializedName("total")
    @Expose
    var total: Integer,
    @SerializedName("current_page")
    @Expose
    var currentPage: Integer,
    @SerializedName("limit")
    @Expose
    var limit: Integer,
) {
}