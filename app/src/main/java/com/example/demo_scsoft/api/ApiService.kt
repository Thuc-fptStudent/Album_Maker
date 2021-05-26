package com.example.demo_scsoft.api

import com.example.demo_scsoft.model.UserRespon
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("/v2/web/user/login/")
    fun login(
        @Field("company_code") company_code: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("os") os : Int,
        @Field("device_token") device_token: String
    ): retrofit2.Call<UserRespon>
}