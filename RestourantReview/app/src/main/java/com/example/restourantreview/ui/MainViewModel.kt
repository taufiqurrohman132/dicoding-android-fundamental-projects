package com.example.restourantreview.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restourantreview.data.respons.CustomerReviewsItem
import com.example.restourantreview.data.respons.PostReviewRespons
import com.example.restourantreview.data.respons.Restaurant
import com.example.restourantreview.data.respons.RestourantResponse
import com.example.restourantreview.data.retrofit.ApiConfig
import com.example.restourantreview.ui.MainActivity.Companion
import com.example.restourantreview.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    // varibel untuk menampung data restoran, review, dan status loading
    private val _restourant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> = _restourant

    private val _listReview = MutableLiveData<List<CustomerReviewsItem>>()
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // variable untuk menyimpan yang akan di tampilkan di snack bar
    // dibungkus dengan Wrapper
    // data yang ingin di jadikan single event
    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    init {
        findRestourant()
    }

    private fun findRestourant(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getRestourant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestourantResponse> {
            override fun onResponse(
                call: Call<RestourantResponse>,
                response: Response<RestourantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
//                    if (responseBody != null){
                        _restourant.value = responseBody?.restaurant
                        _listReview.value = responseBody?.restaurant?.customerReviews
//                    }
                } else Log.e(TAG, "onFailure: ${response.message()}")
            }

            override fun onFailure(call: Call<RestourantResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun postReview(review: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().postReview(RESTAURANT_ID, "El Alii", review)
        client.enqueue(object : Callback<PostReviewRespons>{ // enqueue = untuk menjalankan request secara asycronus di bg, sehingga ui tidk lag
            override fun onResponse( // bisa jadi terdapat respons bisa jadi terdapat kegagalan seperti 404/500, makanya pakai pengecekan
                call: Call<PostReviewRespons>,
                response: Response<PostReviewRespons>
            ) {
                _isLoading.value = false
                val responseBody = response.body() // ini untuk mengambil datanya
                if (response.isSuccessful && responseBody != null) {
                    _listReview.value = responseBody.customerReviews
                    _snackbarText.value = Event(responseBody.message)
                }
                else
                    Log.e(TAG, "onFailure: ${response.message()}")
            }

            override fun onFailure(call: Call<PostReviewRespons>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private const val TAG = "MainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }
}