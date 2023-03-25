package com.example.retrofitrx.retrofit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var outInstance: Retrofit? = null

    val instance: Retrofit
        get() {
            if(outInstance== null){
                outInstance = Retrofit.Builder()
                    .baseUrl(Url.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return outInstance!!
        }
}