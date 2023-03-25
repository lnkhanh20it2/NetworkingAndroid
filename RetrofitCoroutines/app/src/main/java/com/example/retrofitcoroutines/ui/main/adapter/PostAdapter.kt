package com.example.httprequest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcoroutines.data.model.Post
import com.example.retrofitcoroutines.databinding.ItemPostBinding

class PostAdapter( private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    inner class PostViewHolder(val itemBinding: ItemPostBinding)
        :RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(post: Post){
            itemBinding.tvPostId.text = post.id.toString()
            itemBinding.tvPostTitle.text = post.title
            itemBinding.tvPostBody.text = post.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bindItem(post)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    fun addData(list: List<Post>) {
        postList.addAll(list)
    }
}