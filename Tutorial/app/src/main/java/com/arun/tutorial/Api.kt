package com.arun.tutorial

import com.arun.tutorial.model.user
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("users")
    fun getUsers():Call<List<user>>
}