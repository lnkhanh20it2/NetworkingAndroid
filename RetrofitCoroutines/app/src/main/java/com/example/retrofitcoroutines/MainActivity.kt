package com.example.retrofitcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.httprequest.adapter.PostAdapter
import com.example.retrofitcoroutines.data.api.ApiHelper
import com.example.retrofitcoroutines.data.api.RetrofitClient
import com.example.retrofitcoroutines.data.model.Post
import com.example.retrofitcoroutines.databinding.ActivityMainBinding
import com.example.retrofitcoroutines.ui.base.ViewModelFactory
import com.example.retrofitcoroutines.ui.main.viewmodel.PostViewModel
import com.example.retrofitcoroutines.untils.Status

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    private lateinit var postViewModel: PostViewModel
    private lateinit var adapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViewModel()
        setupUI()
        setupObservers()

    }
    private fun setupObservers() {
        postViewModel.getPosts().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { posts -> retrieveList(posts) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
    private fun retrieveList(posts: List<Post>) {
        adapter.addData(posts)
        adapter.notifyDataSetChanged()
    }
    private fun setupUI() {
        binding?.rvPost?.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(arrayListOf())
        binding?.rvPost?.addItemDecoration(
            DividerItemDecoration(
                binding?.rvPost?.context,
                (binding?.rvPost?.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding?.rvPost?.adapter = adapter
    }
    private fun setupViewModel() {
        postViewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitClient.apiService))).get(
                PostViewModel::class.java
            )
    }
}
