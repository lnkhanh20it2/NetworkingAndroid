package com.example.retrofitcoroutines.data.repository

import com.example.retrofitcoroutines.data.api.ApiHelper


class PostRepository(private val apiHelper: ApiHelper) {
    suspend fun getPosts() = apiHelper.getPosts()
}