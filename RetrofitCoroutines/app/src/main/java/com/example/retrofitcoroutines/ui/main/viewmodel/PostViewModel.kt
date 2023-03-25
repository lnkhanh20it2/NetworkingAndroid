package com.example.retrofitcoroutines.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.retrofitcoroutines.data.repository.PostRepository
import com.example.retrofitcoroutines.untils.Resource
import kotlinx.coroutines.Dispatchers

class PostViewModel(private val postRepository: PostRepository): ViewModel() {

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = postRepository.getPosts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}