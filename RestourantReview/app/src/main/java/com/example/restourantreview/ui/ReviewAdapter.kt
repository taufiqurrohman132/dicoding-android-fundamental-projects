package com.example.restourantreview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.restourantreview.data.respons.CustomerReviewsItem
import com.example.restourantreview.databinding.ItemReviewBinding

class ReviewAdapter : ListAdapter<CustomerReviewsItem, ReviewAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemReviewBinding) : ViewHolder(binding.root) {
        fun bind(reviiew: CustomerReviewsItem){
            binding.itemTv.text = "${reviiew.review}\n- ${reviiew.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val reviiew = getItem(position)
        holder.bind(reviiew)

    }
    
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CustomerReviewsItem>() { // diff util = untuk memeriksa apa kah sayng di manipulasi masih sama atau tidak
            override fun areItemsTheSame(// memeriksa id/key yang unik, unuk menegetahui apa ada perubahan posisi dan manipulasi data
                oldItem: CustomerReviewsItem,
                newItem: CustomerReviewsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(// memeriksa apakah  kontent dari dua item sama/tidak
                oldItem: CustomerReviewsItem,
                newItem: CustomerReviewsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}