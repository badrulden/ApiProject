package com.example.tasklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_list.view.*

class MyAdapter(val context: Context, val  userList: List<MyDataItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userId: TextView
        val title: TextView

        init {
            userId = itemView.tv_titlename
            title = itemView.tv_description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userList[position].name.toString()
        holder.title.text = userList[position].body
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}