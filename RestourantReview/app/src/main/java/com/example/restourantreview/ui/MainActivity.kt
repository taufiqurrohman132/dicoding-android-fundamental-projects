package com.example.restourantreview.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.restourantreview.R
import com.example.restourantreview.data.respons.CustomerReviewsItem
import com.example.restourantreview.data.respons.PostReviewRespons
import com.example.restourantreview.data.respons.Restaurant
import com.example.restourantreview.data.respons.RestourantResponse
import com.example.restourantreview.data.retrofit.ApiConfig
import com.example.restourantreview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // inisialisasi pakai activity ktx
    private val mainViewModel by viewModels<MainViewModel>()
    // atau
//    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

//        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
//        findRestourant()

        // harapannya ketika terjadi sonfiguration change itu tidak di observe lagi
        mainViewModel.listReview.observe(this){ customerReview ->
            setReviewData(customerReview)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }

        mainViewModel.snackbarText.observe(this){
            it.getContentIfNotHandled()?.let { text -> // let hanya menjalankan ketika objek tidak bernilai null
                Snackbar.make(window.decorView.rootView, text, Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btaSend.setOnClickListener { view ->
            mainViewModel.postReview(binding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }

    private fun setRestourantData(restourant: Restaurant){
        binding.tvTitle.text = restourant.name
        binding.tvDeskription.text = restourant.description
        Glide.with(this@MainActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restourant.pictureId}")
            .into(binding.ivPicture)
    }

    private fun setReviewData(consumerReview: List<CustomerReviewsItem>){
        val adapter = ReviewAdapter()
        adapter.submitList(consumerReview) // tempat memasukkan data
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.prograsBar.visibility = View.VISIBLE
        }else{
            binding.prograsBar.visibility = View.GONE
        }
    }

    companion object{
        private const val TAG = "MainActivity"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }
}