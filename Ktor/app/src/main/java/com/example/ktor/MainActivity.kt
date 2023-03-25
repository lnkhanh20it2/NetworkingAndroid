package com.example.ktor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ktor.network.model.Post
import com.example.ktor.repository.GetRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            val post = Post(
                body = "this body",
                id = 1,
                title = "this is title",
                userId = 1
            )

            Log.d("TAG","onCreate: ${GetRepo().postPost(post)}")
        }
    }
}