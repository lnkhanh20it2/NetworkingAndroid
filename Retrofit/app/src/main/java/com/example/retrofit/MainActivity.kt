package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.httprequest.adapter.PostAdapter
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.PostItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var postAdapter: PostAdapter

    var binding: ActivityMainBinding? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//     setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.rvPost?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<PostItem>> {
            override fun onResponse(
                call: Call<List<PostItem>>,
                response: Response<List<PostItem>>
            ) {
                val responseBody = response.body()!!

                postAdapter = PostAdapter(responseBody)
                binding?.rvPost?.adapter = postAdapter

            }

            override fun onFailure(call: Call<List<PostItem>>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }

        })

    }
}

