package com.example.tasklist

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("comments")
    fun getData(): Call<List<MyDataItem>>
}