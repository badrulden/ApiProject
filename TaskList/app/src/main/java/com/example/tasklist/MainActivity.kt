package com.example.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleviewuser.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recycleviewuser.layoutManager = linearLayoutManager

        getMyData();
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        //Ctrl+Shift+Space
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                val responseBody = response.body()

                myAdapter = responseBody?.let { MyAdapter(baseContext, it) }!!
                myAdapter.notifyDataSetChanged()
                recycleviewuser.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("MainActivity","onFailure:" +t.message)
            }
        })

    }
}