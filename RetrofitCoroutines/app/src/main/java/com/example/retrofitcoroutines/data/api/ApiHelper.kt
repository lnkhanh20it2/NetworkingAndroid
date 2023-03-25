package com.example.retrofitcoroutines.data.api

class ApiHelper(private val apiServices:MyApi) {
    suspend fun getPosts() = apiServices.getPosts()
}