package com.example.retrofitcoroutines.data.api

import com.example.retrofitcoroutines.data.model.Post
import retrofit2.http.GET

interface MyApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}