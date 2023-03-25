package com.example.retrofitrx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.httprequest.adapter.PostAdapter
import com.example.retrofit.model.PostItem
import com.example.retrofitrx.databinding.ActivityMainBinding
import com.example.retrofitrx.retrofit.MyApi
import com.example.retrofitrx.retrofit.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    lateinit var jsonApi: MyApi
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //init api
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(MyApi::class.java)

        //view
        binding?.rvPost?.layoutManager = LinearLayoutManager(this)
        jsonApi.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{posts->displayData(posts)}

    }
    private fun displayData(posts: List<PostItem>?) {
        val adapter = PostAdapter(this,posts!!)
        binding?.rvPost?.adapter = adapter
    }
}