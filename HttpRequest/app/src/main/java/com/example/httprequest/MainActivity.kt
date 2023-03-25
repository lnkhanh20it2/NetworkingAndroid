package com.example.httprequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.httprequest.adapter.PostAdapter
import com.example.httprequest.databinding.ActivityMainBinding
import com.example.httprequest.model.Post
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var mListPost = ArrayList<Post>()
    var binding: ActivityMainBinding? = null
    private val URL = "https://jsonplaceholder.typicode.com/posts"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.rvPost?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        try {
            run()
        } catch (e: IOException){
            e.printStackTrace()
        }
    }

    private fun run() {
        var client = OkHttpClient()

        var request = Request.Builder()
            .url(URL)
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
            }

            override fun onResponse(call: Call, response: Response)  {
                response.use {
                    if(!response.isSuccessful) throw IOException("Unexpected code $response")
                    var jsonData = response.body!!.string()
                    val jsonArray = JSONArray(jsonData)
                    for (i in 0..10){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val postId = jsonObject.getString("id")
                        val postTitle = jsonObject.getString("title")
                        val postBody = jsonObject.getString("body")
                        val post = Post(postId,postTitle,postBody)
                        mListPost.add(post)
                    }
                    runOnUiThread {
                        val adapter = PostAdapter(mListPost)
                        binding?.rvPost?.adapter = adapter
                    }
                }
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}