package com.example.restourantreview.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// clas untuk membuat dan mengonfigurasi retrofit
class ApiConfig {
    companion object{
        fun getApiService(): ApiService{
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()// http client yang di gunakan oleh retrofit untuk request dan menerima respons
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://restaurant-api.dicoding.dev/")
                .addConverterFactory(GsonConverterFactory.create()) // mengonversi dari json/xml ke objek yang didefinisikan di kode, yang juga parsing otomatis dan menggunakan @serialixe pada masing2 pojo
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}