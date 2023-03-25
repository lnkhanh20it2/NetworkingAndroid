package com.example.retrofitrx.retrofit

import com.example.retrofit.model.PostItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MyApi {
    @get:GET("posts")
    val posts:Observable<List<PostItem>>
}