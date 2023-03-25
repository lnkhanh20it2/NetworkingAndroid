package com.example.ktor.repository

import com.example.ktor.network.KtorClient
import com.example.ktor.network.model.Comment
import com.example.ktor.network.model.Post
import io.ktor.client.request.*

class GetRepo {
    suspend fun getPosts(): List<Post> =
        KtorClient.httpClient.
        get("https://jsonplaceholder.typicode.com/posts")

    suspend fun getComments(id: String): List<Comment> =
        KtorClient.httpClient.get<List<Comment>> {
            url("https://jsonplaceholder.typicode.com/comments")
            parameter("postId", id)

        }
    suspend fun postPost(post: Post): Post =
        KtorClient.httpClient.post{
            url("https://jsonplaceholder.typicode.com/posts")
            body = post
        }
}