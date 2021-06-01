package com.example.demo_scsoft.api

import com.example.demo_scsoft.model.LoginRequest
import com.example.demo_scsoft.model.LoginResponse
import com.example.demo_scsoft.model_getlist.Data
import com.example.demo_scsoft.model_getlist.Example
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("api/v2/app/user/login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>



    @GET("api/v2/app/albums")
    fun getAlbum(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("search") search: String = "",
        @Query("sort[date]") sortDate	: String = "DESC"
    ): Call<Example>
}