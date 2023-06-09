package com.example.retrofit

import com.example.retrofit.model.PostItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getData(): Call<List<PostItem>>
}