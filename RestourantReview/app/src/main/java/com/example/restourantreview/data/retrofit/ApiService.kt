package com.example.restourantreview.data.retrofit

import com.example.restourantreview.data.respons.PostReviewRespons
import com.example.restourantreview.data.respons.RestourantResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

// interface yang berisi kumpulan end point dari url api yang di gunakan pada aplikasi
interface ApiService {
    @GET("detail/{id}") // id nya di ubah dari anotasion path
    fun getRestourant(
        @Path("id") id: String
    ) : Call<RestourantResponse>

    @FormUrlEncoded // wajib, gunanya untuk mengirim data pakai @field
    @Headers("Authorization: token 12345") // untuk menyematkan tokan jika api membutuhkan otorisasi
    @POST("review")
    fun postReview(
        @Field("id") id: String,
        @Field("name") name:String,
        @Field("review") review: String
    ): Call<PostReviewRespons>
}