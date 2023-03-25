package com.example.retrofitcoroutines.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitcoroutines.data.api.ApiHelper
import com.example.retrofitcoroutines.data.repository.PostRepository
import com.example.retrofitcoroutines.ui.main.viewmodel.PostViewModel

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostViewModel::class.java)){
            return PostViewModel(PostRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}